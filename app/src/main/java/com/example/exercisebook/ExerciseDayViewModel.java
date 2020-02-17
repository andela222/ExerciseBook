package com.example.exercisebook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseDayViewModel extends AndroidViewModel {
    private ExerciseDayRepository repository;


    public ExerciseDayViewModel(@NonNull Application application) {
        super(application);
        repository = new ExerciseDayRepository(application);
    }

    public void insert(ExerciseDay day){
        repository.insert(day);
    }

    public void update(ExerciseDay day){
        repository.update(day);
    }

    public void delete(ExerciseDay day){
        repository.delete(day);
    }


    public LiveData<List<ExerciseDay>> getAllDaysByUserId(long id) {
        return repository.getAllDaysByUserId(id);
    }

    public LiveData<ExerciseDay> getLatestInsertedExerciseDay(long id){
        return repository.getLatestInsertedExerciseDay(id);
    }
}
