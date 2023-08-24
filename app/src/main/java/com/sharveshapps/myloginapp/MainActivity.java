package com.sharveshapps.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email,password;
        email= (EditText) findViewById(R.id.editTextEmail);
        password= (EditText)findViewById(R.id.editTextPassword);
        Bundle bundle = getIntent().getExtras();
            String Email = bundle.getString("Email");
            String Password = bundle.getString("Pass");
            email.setText(Email);
            password.setText(Password);
        Button LoginBtn44 = (Button)findViewById(R.id.loginBtn3);
            LoginBtn44.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            });



    }

}