package com.example.exercisebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditUserActivity extends AppCompatActivity {
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private long userId;

    public static final String EXTRA_USER_ID =
            "com.example.exercisebook.EXTRA_FIRST_ID";

    public static final String EXTRA_FIRST_NAME =
            "com.example.exercisebook.EXTRA_FIRST_NAME";

    public static final String EXTRA_LAST_NAME =
            "com.example.exercisebook.EXTRA_LAST_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextFirstName = findViewById(R.id.editFirstName);
        editTextLastName = findViewById(R.id.editLastName);

        Intent intent = getIntent();
        userId = intent.getLongExtra(EXTRA_USER_ID, -1);

        if(intent.hasExtra(EXTRA_USER_ID)){
            setTitle("Edit User");
            editTextFirstName.setText(intent.getStringExtra(EXTRA_FIRST_NAME));
            editTextLastName.setText(intent.getStringExtra(EXTRA_LAST_NAME));
        } else{
            setTitle("Add User");
        }

        handleUserPrompt();
    }

    private void handleUserPrompt(){
        FloatingActionButton saveButton = findViewById(R.id.saveUserButton);
        FloatingActionButton cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFirstName = editTextFirstName.getText().toString();
                String userLastName = editTextLastName.getText().toString();


                if(userFirstName.trim().isEmpty() || userLastName.trim().isEmpty()){
                    Toast.makeText(AddEditUserActivity.this, "Please enter name and surname.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }


                Intent intent = new Intent();
                intent.putExtra(EXTRA_FIRST_NAME, userFirstName);
                intent.putExtra(EXTRA_LAST_NAME, userLastName);


                if(userId != -1){
                    intent.putExtra(EXTRA_USER_ID, userId);
                }

                setResult(RESULT_OK, intent);
                finish();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEditUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
