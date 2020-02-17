package com.example.exercisebook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
@Dao
public interface MeasurementDayDAO {

    @Query("SELECT Id, userId, date FROM MeasurementDay WHERE userId=:userId ORDER BY date DESC")
    LiveData<List<MeasurementDay>> getAllDaysByUserId(long userId);

    @Query("SELECT Id, userId, date, height, weight, shoulderWidth, chestWidth, waistWidth, hipsWidth, thighsWidth, upperArmWidth  " +
            "FROM MeasurementDay WHERE Id=:Id")
    LiveData<MeasurementDay> getDayById(long Id);

    @Insert
    void insert(MeasurementDay day);

    @Delete
    void delete(MeasurementDay day);

    @Update
    void update(MeasurementDay day);

}
