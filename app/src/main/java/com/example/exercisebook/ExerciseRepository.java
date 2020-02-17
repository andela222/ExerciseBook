package com.example.exercisebook;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseRepository {
    private ExerciseDAO exerciseDAO;

    public ExerciseRepository(Application application){
        AppDatabase database = AppDatabase.getAppDatabase(application);
        exerciseDAO = database.exerciseDAO();
    }
    public void insert(Exercise... exercises){
        new ExerciseRepository.InsertExerciseAsyncTask(exerciseDAO).execute(exercises);
    }

    public void update(List<Exercise> exercises){
        new ExerciseRepository.UpdateExerciseAsyncTask(exerciseDAO).execute(new MyTaskParams(exercises));
    }

    public void delete(Exercise exercise){
        new ExerciseRepository.DeleteExerciseAsyncTask(exerciseDAO).execute(exercise);
    }

    public void InsertAllWithParent(ExerciseDay day, List<Exercise> exercises){
        new InsertAllAsyncTask(exerciseDAO).execute(new MyTaskParams(day, exercises));
    }
    public LiveData<List<Exercise>> getAllExercisesByDayId(long id) { return exerciseDAO.getAllExercisesById(id);}
    /**
     We want to do our database operations(insert, update, delete)
     on a different thread (in the background) so we are using async task.
     Room does not let doing these operations on the main thread.
     **/
    private static class InsertExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDAO exerciseDAO;

        private InsertExerciseAsyncTask(ExerciseDAO exerciseDAO){
            this.exerciseDAO= exerciseDAO;
        }

        @Override
        protected Void doInBackground(Exercise... exercises){
            exerciseDAO.insert(exercises);
            return null;
        }
    }

    private static class UpdateExerciseAsyncTask extends AsyncTask<MyTaskParams, Void, Void>{
        private ExerciseDAO exerciseDAO;

        private UpdateExerciseAsyncTask(ExerciseDAO exerciseDAO){
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(MyTaskParams... params){
            exerciseDAO.update(params[0].exercises);
            return null;
        }
    }

    private static class DeleteExerciseAsyncTask extends AsyncTask<Exercise, Void, Void>{
        private ExerciseDAO exerciseDAO;

        private DeleteExerciseAsyncTask(ExerciseDAO exerciseDAO){
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(Exercise... exercises){
            exerciseDAO.delete(exercises[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<MyTaskParams, Void, Void>{
        private ExerciseDAO exerciseDAO;

        private InsertAllAsyncTask(ExerciseDAO exerciseDAO){
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(MyTaskParams... myTaskParams) {
            exerciseDAO.InsertAllWithParent(myTaskParams[0].day, myTaskParams[0].exercises);
            return null;
        }
    }

    private static class MyTaskParams {
        ExerciseDay day;
        List<Exercise> exercises;

        MyTaskParams(ExerciseDay day, List<Exercise> exercises) {
            this.day = day;
            this.exercises = exercises;
        }

        MyTaskParams(List<Exercise> exercises){
            this.exercises = exercises;
        }
    }
}
