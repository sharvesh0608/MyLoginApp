package com.sharveshapps.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String storedEmail = sharedPreferences.getString("email", null);
        String storedPassword = sharedPreferences.getString("password", null);
        if (storedEmail != null && storedPassword != null) {
            // Credentials are available, so navigate to the main screen
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish(); // Close this activity to prevent going back to the login screen
        } else {
            // Credentials are not available, show the login screen
            setContentView(R.layout.activity_main);

            // The rest of your existing code for setting up the login screen
            // ...

            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            Log.d(TAG, "onCreate called");
            EditText email, password;
            email = (EditText) findViewById(R.id.editTextFullName);
            password = (EditText) findViewById(R.id.editTextPassword);
            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                String Email = bundle.getString("Email", "Default");
                String Password = bundle.getString("Pass", " Default");
                email.setText(Email);
                password.setText(Password);
            }
            Button LoginBtn44 = (Button) findViewById(R.id.loginBtn3);
            TextView infoid = (TextView) findViewById(R.id.infoId);
            LoginBtn44.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String enteredEmail = email.getText().toString();
                    String enteredPassword = password.getText().toString();

                    if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Both email and password are required", Toast.LENGTH_SHORT).show();
                    } else if (isValidEmail(enteredEmail) && isValidPassword(enteredPassword)) {
                        DatabaseHelper dbHelper = new DatabaseHelper(LogIn.this);

                        if (dbHelper.verifyCredentials(enteredEmail, enteredPassword)) {
                            // Valid credentials: Store in shared preferences and navigate to the main screen
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", enteredEmail);
                            editor.putString("password", enteredPassword);
                            editor.apply();
                            Intent intent = new Intent(LogIn.this, Home.class);
                            startActivity(intent);
                            finish(); // Optional: close this activity to prevent going back to login screen
                        } else {
                            // Invalid credentials: Show error message
                            Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Show validation error messages
                        if (!isValidEmail(enteredEmail)) {
                            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                        } else if (!isValidPassword(enteredPassword)) {
                            Toast.makeText(getApplicationContext(), "Invalid Password (at least 8 characters required)", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


            infoid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LogIn.this, signup.class);
                    startActivity(intent);

                }
            });
        }


    }
    @Override
    public void onBackPressed() {
        // Close the entire app when back button is pressed on the login screen
        finishAffinity();
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