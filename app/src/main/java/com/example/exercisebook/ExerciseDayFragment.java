package com.example.exercisebook;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDayFragment extends Fragment {
    private Long userId;
    public static ExerciseDayViewModel dayViewModel;
    public ExerciseDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ExerciseDayAdapter adapter = new ExerciseDayAdapter();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.exercise_days_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewExerciseDays);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();
        userId = bundle.getLong("userId");


        dayViewModel = ViewModelProviders.of(this).get(ExerciseDayViewModel.class);
        dayViewModel.getAllDaysByUserId(userId).observe(this, new Observer<List<ExerciseDay>>() {
            @Override
            public void onChanged(List<ExerciseDay> days) {
                adapter.setDays(days);
            }
        });

        adapter.setOnWorkoutCardClickListener(new ExerciseDayAdapter.OnWorkoutCardClickListener() {
            @Override
            public void OnWorkoutCardClick(ExerciseDay day) {
                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                intent.putExtra(AddEditExercisesActivity.EXTRA_DAY_ID, day.getId());
                startActivity(intent);
            }
        });
        adapter.setOnWorkoutEditClickListener(new ExerciseDayAdapter.OnWorkoutEditClickListener() {
            @Override
            public void OnWorkoutEditClick(ExerciseDay day) {
                Intent intent = new Intent(getActivity(), AddEditExercisesActivity.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                intent.putExtra(AddEditExercisesActivity.EXTRA_DAY_ID, day.getId());
                startActivity(intent);
            }
        });

        adapter.setOnWorkoutDeleteClickListener(new ExerciseDayAdapter.OnWorkoutDeleteClickListener() {
            @Override
            public void OnWorkoutDeleteClick(ExerciseDay day) {
                dayViewModel.delete(day);
            }
        });

        return view;
    }

}
