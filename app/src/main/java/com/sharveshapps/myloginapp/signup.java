package com.sharveshapps.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {
    Button signupBtn;
    EditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupBtn = (Button) findViewById(R.id.signBtn);
        name = (EditText) findViewById(R.id.editTextEmail);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextPassword);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Pass = password.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("Email", Email);
                bundle.putString("Pass", Pass);
                Intent intent = new Intent(signup.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





    }
}