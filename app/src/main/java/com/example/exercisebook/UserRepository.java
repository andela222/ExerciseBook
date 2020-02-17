package com.example.exercisebook;

import android.app.Application;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        AppDatabase database = AppDatabase.getAppDatabase(application);
        userDAO = database.userDao();
        allUsers = userDAO.getAllUsers();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDAO).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    //live data doesn't need async task since it's already async
    public LiveData<List<User>> getAllUsers(){
        return userDAO.getAllUsers();
    }

    /**
     We want to do our database operations(insert, update, delete)
     on a different thread (in the background) so we are using async task.
     Room does not let us doing these operations on the main thread.
    **/
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users){
            userDAO.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO userDAO;

        private UpdateUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users){
            userDAO.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users){
            userDAO.delete(users[0]);
            return null;
        }
    }
}
