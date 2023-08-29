package com.sharveshapps.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    Button signupBtn;
    EditText name, email, password, mob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate called");
        signupBtn = (Button) findViewById(R.id.signBtn);
        name = (EditText) findViewById(R.id.editTextFullName);
        email = (EditText) findViewById(R.id.editTexttEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        mob = (EditText) findViewById(R.id.editTextMob);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Pass = password.getText().toString();
                String Mob = mob.getText().toString();

                if (Name.isEmpty() || Email.isEmpty() || Pass.isEmpty() || Mob.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!isValidName(Name)) {
                    Toast.makeText(getApplicationContext(), "Invalid Name (only characters allowed)", Toast.LENGTH_SHORT).show();
                } else if (!isValidMobile(Mob)) {
                    Toast.makeText(getApplicationContext(), "Invalid Mobile Number (only valid numbers allowed)", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(Email)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(Pass)) {
                    Toast.makeText(getApplicationContext(), "Invalid Password (at least 8 characters required)", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform sign-up and navigation logic here
                    DatabaseHelper dbHelper = new DatabaseHelper(signup.this);
                    long result = dbHelper.insertUser(Name, Email, Mob, Pass);
                    if (result != -1) {
                        Toast.makeText(getApplicationContext(), "Signup Successful and Data Inserted into DB", Toast.LENGTH_SHORT).show();
                        // Rest of your navigation code
                    } else {
                        Toast.makeText(getApplicationContext(), "Error inserting data into DB", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(signup.this, LogIn.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Email", Email);
                    bundle.putString("Pass", Pass);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        TextView iiinfo = (TextView) findViewById(R.id.IIInfo);
        iiinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, LogIn.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    private boolean isValidName(String name) {
        String namePattern = "^[a-zA-Z]+$"; // Only characters
        return name.matches(namePattern);
    }

    private boolean isValidMobile(String mobile) {
        String mobilePattern = "^[0-9]{10}$"; // Exactly 10 digits
        return mobile.matches(mobilePattern);
    }

    private boolean isValidEmail(String email) {
        // You can use a regular expression to validate the email format
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        // You can define your own criteria for password validation
        return password.length() >= 8;
    }
}