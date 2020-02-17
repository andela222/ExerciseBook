package com.example.exercisebook;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "ExerciseDay",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "Id",
                childColumns = "userId",
                onDelete = CASCADE //master-detail relationship
        ),
        indices = @Index("userId"))
public class ExerciseDay {
    @PrimaryKey(autoGenerate = true)
    private long Id;
    //lookup on user so we know which user had this exercise
    private long userId;
    @TypeConverters(DateRoomConverter.class)
    private Date date;

    public ExerciseDay(long userId, Date date) {
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
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
         return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
