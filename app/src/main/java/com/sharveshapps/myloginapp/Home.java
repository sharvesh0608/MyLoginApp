package com.sharveshapps.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<User> userList = databaseHelper.getAllUsers();
        UserAdapter userAdapter = new UserAdapter(userList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(userAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}