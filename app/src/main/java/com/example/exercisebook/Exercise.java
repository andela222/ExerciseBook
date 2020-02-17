package com.example.exercisebook;

import android.util.SparseArray;
import android.util.SparseIntArray;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.HashMap;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Exercise",
        foreignKeys = @ForeignKey(
                entity = ExerciseDay.class,
                parentColumns = "Id",
                childColumns = "dayId",
                onDelete = CASCADE
        ),
        indices = @Index("dayId"))
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    long Id;
    long dayId;

    String name;
    Integer numberOfSets;
    Double weight;

    @TypeConverters(SparseIntArrayConverter.class)
    public SparseIntArray repetitionsBySet = new SparseIntArray();

    public long getId(){
        return this.Id;
    }

    public void setDayId(long dayId){
        this.dayId = dayId;
    }

    public long getDayId(){
        return this.dayId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setNumberOfSets(Integer number){
        this.numberOfSets = number;
    }

    public Integer getNumberOfSets(){
        return this.numberOfSets;
    }

    public void setWeight(Double weight){
        this.weight = weight;
    }

    public Double getWeight(){
        return this.weight;
    }
}
