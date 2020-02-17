package com.example.exercisebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEditExercisesActivity extends AppCompatActivity {
    public static final String EXTRA_DAY_ID = "com.example.exercisebook.EXTRA_DAY_ID";

    private ExerciseViewModel exerciseViewModel;

    private long dayId;
    private long userId;

    private Date date = new Date();

    private List<String> pickedExerciseNames = new ArrayList<>();
    private List<Exercise> exercisesForInsert = new ArrayList<>();


    private String[] allExercises = new String[]{"Squat", "Bench Press", "Dumbell Row", "Military Press",
            "Tricep Pushdown", "Lying Tricep Press", "Side Lateral Rises", "Preaching Curls", "Seated Dumbell Curls",
            "One Arm Dumbell Row", "Lat Pushdowns", "Shrugs", "Chinups", "Leg Press", "Leg Extension", "Leg Curls",
            "Calves Raises","Ab Crunches", "Crunches", "Dips", "Butterfly", "EZ Bar Curls", "Hammer Curls", "Deadlift"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_layout);

        exerciseViewModel = ViewModelProviders.of(AddEditExercisesActivity.this).get(ExerciseViewModel.class);

        Intent intent = getIntent();
        long dateLong = intent.getLongExtra(PickDate.EXTRA_DATE, -1);
        if(dateLong != -1){
            date.setTime(dateLong);
        }

        userId = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID, -1);
        dayId = intent.getLongExtra(AddEditExercisesActivity.EXTRA_DAY_ID, -1);


        NumberPicker numOfSetsPicker = findViewById(R.id.setPicker);
        numOfSetsPicker.setMinValue(1);
        numOfSetsPicker.setMaxValue(8);

        setUpNamePicker();

        FloatingActionButton addExerciseBtn = findViewById(R.id.addExercise);
        FloatingActionButton finishBtn = findViewById(R.id.saveExercises);


        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weightPicker = findViewById(R.id.weightPicker);
                if(weightPicker.getText().toString().trim().length() > 0){
                    if(pickedExerciseNames.size() >= 10){
                        Toast.makeText(AddEditExercisesActivity.this, "Exercise not added. Cannot add more then 10 exercises per day.", Toast.LENGTH_LONG).show();
                    } else {
                        Exercise exercise = new Exercise();
                        NumberPicker namePicker = findViewById(R.id.exerciseNamePicker);
                        NumberPicker setPicker = findViewById(R.id.setPicker);

                        exercise.setName(namePicker.getDisplayedValues()[namePicker.getValue()]);
                        exercise.setNumberOfSets(setPicker.getValue());
                        exercise.setWeight(Double.parseDouble(weightPicker.getText().toString()));

                        exercisesForInsert.add(exercise);
                        pickedExerciseNames.add(exercise.getName());
                        setPicker.setValue(1);
                        weightPicker.setText("");
                        setUpNamePicker();

                        Toast.makeText(AddEditExercisesActivity.this, "Exercise added", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(AddEditExercisesActivity.this, "Weight is empty. Exercise not added", Toast.LENGTH_LONG).show();
                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pickedExerciseNames.isEmpty()){
                    Toast.makeText(AddEditExercisesActivity.this, "You need to pick at least one exercise", Toast.LENGTH_LONG).show();
                }else{
                    if(dayId == -1){
                        if(userId != -1 && date != null){
                            ExerciseDay day = new ExerciseDay(userId, date);
                            exerciseViewModel.insertAllWithParent(day, exercisesForInsert);
                            Intent intent = new Intent(AddEditExercisesActivity.this, WorkoutActivity.class);
                            intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                            startActivity(intent);

                        }
                    } else {
                        insertExercises(dayId);

                        Intent intent = new Intent(AddEditExercisesActivity.this, WorkoutActivity.class);
                        intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                        intent.putExtra(EXTRA_DAY_ID, dayId);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dayId != -1){
            pickedExerciseNames = new ArrayList<>();

            exerciseViewModel.getAllExercisesByDayId(dayId).observe(this, new Observer<List<Exercise>>() {
                @Override
                public void onChanged(List<Exercise> exercises) {

                    for(Exercise exercise: exercises){
                        //querying already picked exercises for this day so they can be removed from the name picker
                        pickedExerciseNames.add(exercise.getName());
                        setUpNamePicker();
                    }
                }
            });
        }
    }

    private void setUpNamePicker(){

        List<String> notPickedList = new ArrayList<String>();

        for(String exerciseName: allExercises){
            if(!pickedExerciseNames.contains(exerciseName)){
                notPickedList.add(exerciseName);
            }
        }

        String[] notPickedExercises = new String[notPickedList.size()];
        notPickedExercises = notPickedList.toArray(notPickedExercises);

        NumberPicker exercisePicker = findViewById(R.id.exerciseNamePicker);
        exercisePicker.setMinValue(0);
        exercisePicker.setMaxValue(notPickedExercises.length - 1);
        exercisePicker.setDisplayedValues(notPickedExercises);

    }

    private void insertExercises(long dayId){

        for(Exercise exercise: exercisesForInsert){
            exercise.setDayId(dayId);
        }

        Exercise[] formattedExercises = new Exercise[exercisesForInsert.size()];
        formattedExercises = exercisesForInsert.toArray(formattedExercises);
        exerciseViewModel.insert(formattedExercises);

        exercisesForInsert.clear();
    }
}
