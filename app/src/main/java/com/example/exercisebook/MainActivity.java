package com.example.exercisebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_USER_REQUEST = 1;
    public static final int EDIT_USER_REQUEST = 2;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserAdapter adapter = new UserAdapter();

        FloatingActionButton buttonAddUser = findViewById(R.id.buttonAddUser);

        //setting listener for button for adding a user
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class);
                startActivityForResult(intent, ADD_USER_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });

        //edit
        adapter.setOnButtonItemClickListener(new UserAdapter.OnItemButtonClickListener() {
            @Override
            public void onItemButtonClick(User user) {
                Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, user.getId());
                intent.putExtra(AddEditUserActivity.EXTRA_FIRST_NAME, user.getFirstName());
                intent.putExtra(AddEditUserActivity.EXTRA_LAST_NAME, user.getLastName());

                startActivityForResult(intent, EDIT_USER_REQUEST);
            }
        });

        //delete
        adapter.setOnDeleteButtonItemClickListener(new UserAdapter.OnDeleteItemButtonClickListener() {
            @Override
            public void onDeleteItemButtonClick(User user) {
                userViewModel.delete(user);
                Toast.makeText(MainActivity.this, "User deleted.", Toast.LENGTH_LONG).show();
            }
        });

        adapter.setOnUserCardClickListener(new UserAdapter.OnUserCardClickListener() {
            @Override
            public void OnUserCardClickListener(User user) {
                Intent intent = new Intent(MainActivity.this, DisplayTabActivity.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, user.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK){
            String firstName = intent.getStringExtra(AddEditUserActivity.EXTRA_FIRST_NAME);
            String lastName = intent.getStringExtra(AddEditUserActivity.EXTRA_LAST_NAME);

            User user = new User(firstName,lastName);

            userViewModel.insert(user);
            Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show();

        } else if(requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK) {
            long id = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID, -1);
            if(id == -1){
                Toast.makeText(this, "Something went wrong. User cannot be edited.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            String firstName = intent.getStringExtra(AddEditUserActivity.EXTRA_FIRST_NAME);
            String lastName = intent.getStringExtra(AddEditUserActivity.EXTRA_LAST_NAME);

            User user = new User(firstName,lastName);
            user.setId(id);

            userViewModel.update(user);
            Toast.makeText(this, "User updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "User not saved", Toast.LENGTH_LONG).show();
        }
    }

}
