package com.example.exercisebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class AddEditMeasurementsActivity extends AppCompatActivity {

    public static final String EXTRA_MEASUREMENT_DAY_ID =
            "com.example.exercisebook.EXTRA_MEASUREMENT_DAY_ID";

    private Date date = new Date();
    private long dayId;
    private long userId;
    private androidx.lifecycle.Observer<MeasurementDay> dayObserver;
    private EditText editTextWeight;
    private EditText editTextHeight;
    private EditText editTextShoulderWidth;
    private EditText editTextChestWidth;
    private EditText editTextWaistWidth;
    private EditText editTextHipsWidth;
    private EditText editTextThighsWidth;
    private EditText editTextUpperArmWidth;

    private MeasurementDayViewModel dayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_measurements_layout);

        editTextWeight = findViewById(R.id.userWeight);
        editTextHeight = findViewById(R.id.userHeight);
        editTextShoulderWidth = findViewById(R.id.userShoulderWidth);
        editTextChestWidth = findViewById(R.id.userChestWidth);
        editTextWaistWidth = findViewById(R.id.userWaistWidth);
        editTextHipsWidth = findViewById(R.id.userHipsWidth);
        editTextThighsWidth = findViewById(R.id.userThighsWidth);
        editTextUpperArmWidth = findViewById(R.id.userUpperArmWidth);

        dayViewModel = ViewModelProviders.of(AddEditMeasurementsActivity.this).get(MeasurementDayViewModel.class);

        final Intent intent = getIntent();
        long dateLong = intent.getLongExtra(PickDate.EXTRA_DATE, -1);
        if(dateLong != -1){
            date.setTime(dateLong);
        }

        userId = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID, -1);
        dayId = intent.getLongExtra(EXTRA_MEASUREMENT_DAY_ID, -1);

        if(intent.hasExtra(EXTRA_MEASUREMENT_DAY_ID)){
            setTitle("Edit Measurements");
            if(dayId != -1){
                initializeObservers();
                dayViewModel.getDayById(dayId).observe(this, dayObserver);
            }
        } else {
            setTitle("Add New Measurements");
        }

        FloatingActionButton saveMeasurementsBtn = findViewById(R.id.saveMeasurement);

        saveMeasurementsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId != -1 && date != null){
                    String userHeight = editTextHeight.getText().toString();
                    String userWeight = editTextWeight.getText().toString();
                    String userShoulderWidth = editTextShoulderWidth.getText().toString();
                    String userChestWidth = editTextChestWidth.getText().toString();
                    String userWaistWidth = editTextWaistWidth.getText().toString();
                    String userHipsWidth = editTextHipsWidth.getText().toString();
                    String userThighsWidth = editTextThighsWidth.getText().toString();
                    String userUpperArmWidth = editTextUpperArmWidth.getText().toString();

                    if(userHeight.trim().isEmpty() || userWeight.trim().isEmpty() ||
                        userShoulderWidth.trim().isEmpty() || userChestWidth.trim().isEmpty() ||
                        userWaistWidth.trim().isEmpty() || userHipsWidth.trim().isEmpty() ||
                        userThighsWidth.trim().isEmpty() || userUpperArmWidth.trim().isEmpty()){

                        Toast.makeText(AddEditMeasurementsActivity.this, "Please fill in all measurements before saving.", Toast.LENGTH_LONG).show();
                    } else {
                        MeasurementDay measurementDay = new MeasurementDay(userId, date);

                        measurementDay.setHeight(Double.parseDouble(userHeight));
                        measurementDay.setWeight(Double.parseDouble(userWeight));
                        measurementDay.setShoulderWidth(Double.parseDouble( userShoulderWidth));
                        measurementDay.setChestWidth(Double.parseDouble(userChestWidth));
                        measurementDay.setWaistWidth(Double.parseDouble(userWaistWidth));
                        measurementDay.setHipsWidth(Double.parseDouble(userHipsWidth));
                        measurementDay.setThighsWidth(Double.parseDouble(userThighsWidth));
                        measurementDay.setUpperArmWidth(Double.parseDouble(userUpperArmWidth));

                        Log.d("measurementTest","dayID: " + dayId);

                        if(dayId != -1){
                            measurementDay.setId(dayId);
                            dayViewModel.update(measurementDay);
                            Toast.makeText(AddEditMeasurementsActivity.this, "Measurements updated.", Toast.LENGTH_LONG).show();
                            dayViewModel.getDayById(dayId).removeObserver(dayObserver);
                        } else {
                            dayViewModel.insert(measurementDay);
                            Toast.makeText(AddEditMeasurementsActivity.this, "Measurements added.", Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(AddEditMeasurementsActivity.this, DisplayTabActivity.class);
                        intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void initializeObservers() {
        dayObserver = new Observer<MeasurementDay>() {
            @Override
            public void onChanged(MeasurementDay day) {
                editTextHeight.setText(String.valueOf(day.getHeight()));
                editTextWeight.setText(String.valueOf(day.getWeight()));
                editTextShoulderWidth.setText(String.valueOf(day.getShoulderWidth()));
                editTextChestWidth.setText(String.valueOf(day.getChestWidth()));
                editTextWaistWidth.setText(String.valueOf(day.getWaistWidth()));
                editTextHipsWidth.setText(String.valueOf(day.getHipsWidth()));
                editTextThighsWidth.setText(String.valueOf(day.getThighsWidth()));
                editTextUpperArmWidth.setText(String.valueOf(day.getUpperArmWidth()));
            }
        };
    }
}

