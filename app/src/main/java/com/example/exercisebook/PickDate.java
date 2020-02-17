package com.example.exercisebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class PickDate extends AppCompatActivity {
    public static final String EXTRA_DATE = "com.example.exercisebook.EXTRA_DAY_TYPE";
    CalendarView calendarView;
    Date date;
    long userId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        //removing the title
        setTitle("");
        //setting an icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_calendar);


        calendarView = findViewById(R.id.calendar);
        if(calendarView != null){
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month,
                                                int day) {
                    Toast.makeText(getApplicationContext(),day+ "/"+month+"/"+year,Toast.LENGTH_SHORT).show();
                    date = new GregorianCalendar(year, month, day).getTime();
                }
            });
        }

        Intent intent = getIntent();
        userId = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID, -1);
        final String dayType = intent.getStringExtra(DisplayTabActivity.EXTRA_DAY_TYPE);

        FloatingActionButton saveDate = findViewById(R.id.saveDate);

        saveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date == null) {
                    //if nothing is selected date is set on today's date
                    date = Calendar.getInstance().getTime();
                }

                if(dayType.equals("addExerciseDay")){
                    Intent intent = new Intent(PickDate.this, AddEditExercisesActivity.class);
                    intent.putExtra(EXTRA_DATE, date.getTime());
                    intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                    startActivity(intent);

                } else if(dayType.equals("addMeasurementDay")){
                    Intent intent = new Intent(PickDate.this, AddEditMeasurementsActivity.class);
                    intent.putExtra(EXTRA_DATE, date.getTime());
                    intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                    startActivity(intent);
                }
            }
        });
    }
}
