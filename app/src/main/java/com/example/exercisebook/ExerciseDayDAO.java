package com.example.exercisebook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ExerciseDayDAO {

    @Query("SELECT Id, userId, date FROM ExerciseDay WHERE userId=:userId ORDER BY date DESC")
    LiveData<List<ExerciseDay>> getAllDaysByUserId(long userId);

    @Query("SELECT Id, userId, date FROM ExerciseDay WHERE userId=:userId ORDER BY Id DESC LIMIT 1")
    LiveData<ExerciseDay> getLatestInsertedExerciseDay(long userId);

    @Insert
    void insert(ExerciseDay day);

    @Delete
    void delete(ExerciseDay day);

    @Update
    void update(ExerciseDay day);

}
