package com.example.exercisebook;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "MeasurementDay",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "Id",
                childColumns = "userId",
                onDelete = CASCADE
        ),
        indices = @Index("userId"))
public class MeasurementDay {
    @PrimaryKey(autoGenerate = true)
    private long Id;
    //lookup on user so we know whose measurement this is
    private long userId;
    @TypeConverters(DateRoomConverter.class)
    private Date date;

    private Double height;
    private Double weight;
    private Double shoulderWidth;
    private Double chestWidth;
    private Double waistWidth;
    private Double hipsWidth;
    private Double thighsWidth;
    private Double upperArmWidth;


    public MeasurementDay(long userId, Date date) {
        this.userId = userId;
        this.date = date;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHeight() {
        return this.height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return this.weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getShoulderWidth() {
        return this.shoulderWidth;
    }
    public void setShoulderWidth(Double shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public Double getChestWidth() {
        return this.chestWidth;
    }
    public void setChestWidth(Double chestWidth) {
        this.chestWidth = chestWidth;
    }

    public Double getWaistWidth() {
        return this.waistWidth;
    }
    public void setWaistWidth(Double waistWidth) {
        this.waistWidth = waistWidth;
    }

    public Double getHipsWidth() {
        return this.hipsWidth;
    }
    public void setHipsWidth(Double hipsWidth) {
        this.hipsWidth = hipsWidth;
    }

    public Double getThighsWidth() {
        return this.thighsWidth;
    }
    public void setThighsWidth(Double thighsWidth) {
        this.thighsWidth = thighsWidth;
    }

    public Double getUpperArmWidth() {
        return this.upperArmWidth;
    }
    public void setUpperArmWidth(Double upperArmWidth) {
        this.upperArmWidth = upperArmWidth;
    }

}
