package com.example.exercisebook;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MeasurementDayRepository {
    private MeasurementDayDAO measurementDayDAO;

    public MeasurementDayRepository(Application application){
        AppDatabase database = AppDatabase.getAppDatabase(application);
        measurementDayDAO = database.measurementDayDao();
    }
    public void insert(MeasurementDay day){
        new MeasurementDayRepository.InsertDayAsyncTask(measurementDayDAO).execute(day);
    }

    public void update(MeasurementDay day){
        new MeasurementDayRepository.UpdateDayAsyncTask(measurementDayDAO).execute(day);
    }

    public void delete(MeasurementDay day){
        new MeasurementDayRepository.DeleteDayAsyncTask(measurementDayDAO).execute(day);
    }


    public LiveData<List<MeasurementDay>> getAllDaysByUserId(long userId) { return measurementDayDAO.getAllDaysByUserId(userId);}
    public LiveData<MeasurementDay> getDayById(long id){return measurementDayDAO.getDayById(id);}
    /**
     We want to do our database operations(insert, update, delete)
     on a different thread (in the background) so we are using async task.
     Room does not let doing these operations on the main thread.
     **/
    private static class InsertDayAsyncTask extends AsyncTask<MeasurementDay, Void, Void> {
        private MeasurementDayDAO measurementDayDAO;

        private InsertDayAsyncTask(MeasurementDayDAO dayDAO){
            this.measurementDayDAO= dayDAO;
        }

        @Override
        protected Void doInBackground(MeasurementDay... days){
            measurementDayDAO.insert(days[0]);
            return null;
        }
    }

    private static class UpdateDayAsyncTask extends AsyncTask<MeasurementDay, Void, Void>{
        private MeasurementDayDAO measurementDayDAO;

        private UpdateDayAsyncTask(MeasurementDayDAO dayDAO){
            this.measurementDayDAO = dayDAO;
        }

        @Override
        protected Void doInBackground(MeasurementDay... days){
            measurementDayDAO.update(days[0]);
            return null;
        }
    }

    private static class DeleteDayAsyncTask extends AsyncTask<MeasurementDay, Void, Void>{
        private MeasurementDayDAO measurementDayDAO;

        private DeleteDayAsyncTask(MeasurementDayDAO dayDAO){
            this.measurementDayDAO = dayDAO;
        }

        @Override
        protected Void doInBackground(MeasurementDay... days){
            measurementDayDAO.delete(days[0]);
            return null;
        }
    }
}
