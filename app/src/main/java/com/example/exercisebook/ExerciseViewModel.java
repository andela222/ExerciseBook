package com.example.exercisebook;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository repository;


    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExerciseRepository(application);

    }

    public void insert(Exercise... exercises){
        repository.insert(exercises);
    }

    public void update(List<Exercise> exercises){
        repository.update(exercises);
    }

    public void delete(Exercise exercise){
        repository.delete(exercise);
    }

    public void insertAllWithParent(ExerciseDay day, List<Exercise> exercises){ repository.InsertAllWithParent(day, exercises);}

    public LiveData<List<Exercise>> getAllExercisesByDayId(long id) {
        return repository.getAllExercisesByDayId(id);
    }

}
