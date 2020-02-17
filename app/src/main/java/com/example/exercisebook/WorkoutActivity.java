package com.example.exercisebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    private ExerciseDayViewModel dayViewModel;
    private ExerciseViewModel exerciseViewModel;
    RecyclerView recyclerView;
    private final ExerciseAdapter exerciseAdapter = new ExerciseAdapter();
    private Observer<ExerciseDay> dayObserver;
    private Observer<List<Exercise>> exercisesObserver;
    private long userId;
    private long dayId;
    public static List<Exercise> exercisesForUpdate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_items_layout);
        recyclerView = findViewById(R.id.recyclerViewExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(WorkoutActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(exerciseAdapter);

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        dayViewModel = ViewModelProviders.of(this).get(ExerciseDayViewModel.class);
        initializeObservers();

        Intent intent = getIntent();

        userId = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID, -1);
        dayId = intent.getLongExtra(AddEditExercisesActivity.EXTRA_DAY_ID, -1);

        exerciseAdapter.setOnDeleteButtonItemClickListener(new ExerciseAdapter.OnDeleteItemButtonClickListener() {
            @Override
            public void onDeleteItemButtonClick(Exercise exercise) {
                exerciseViewModel.delete(exercise);
                Toast.makeText(WorkoutActivity.this, "Exercise deleted.", Toast.LENGTH_LONG).show();
            }
        });

        if(dayId == -1){
            dayViewModel.getLatestInsertedExerciseDay(userId).observe(this, dayObserver);
        } else {
            exerciseViewModel.getAllExercisesByDayId(dayId).observe(this, exercisesObserver);
        }

        FloatingActionButton finishButton = findViewById(R.id.finishWorkoutBtn);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exercisesForUpdate != null && userId != -1){
                    Intent intent = new Intent(WorkoutActivity.this, DisplayTabActivity.class);

                    exerciseViewModel.update(exercisesForUpdate);
                    Toast.makeText(WorkoutActivity.this, "Workout saved. Total lifted: " + totalLifted(exercisesForUpdate), Toast.LENGTH_LONG).show();
                    intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                    startActivity(intent);
                }
            }
        });

    }
    public void initializeObservers(){

        dayObserver = new Observer<ExerciseDay>() {
            @Override
            public void onChanged(ExerciseDay day) {
                if(day != null){
                    dayId = day.getId();
                    exerciseViewModel.getAllExercisesByDayId(dayId).observe(WorkoutActivity.this, exercisesObserver);
                }
            }
        };

        exercisesObserver = new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseAdapter.setExercises(exercises);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dayViewModel.getLatestInsertedExerciseDay(userId).removeObserver(dayObserver);

        if(dayId != -1){
            exerciseViewModel.getAllExercisesByDayId(dayId).removeObserver(exercisesObserver);
        }
    }

    private Double totalLifted(List<Exercise> exercises){
        Double totalLifted = 0.0;
        for(Exercise exercise: exercises){
            for(Integer i = 0; i < exercise.repetitionsBySet.size(); ++i){
                int key = exercise.repetitionsBySet.keyAt(i);
                totalLifted += exercise.repetitionsBySet.get(key) * exercise.weight;
            }
        }
        return totalLifted;
    }
}
