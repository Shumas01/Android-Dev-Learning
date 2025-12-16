package com.example.signupproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, username, dob, password;
    Button signup;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Links to activity_main.xml

        // Initialize Views
        email = findViewById(R.id.signup_email);
        username = findViewById(R.id.signup_username);
        dob = findViewById(R.id.signup_dob);
        password = findViewById(R.id.signup_password);
        signup = findViewById(R.id.btn_signup);

        // Initialize Database
        DB = new DBHandler(this);

        signup.setOnClickListener(v -> {
            String emailTXT = email.getText().toString();
            String userTXT = username.getText().toString();
            String dobTXT = dob.getText().toString();
            String passTXT = password.getText().toString();

            if(emailTXT.equals("") || userTXT.equals("") || dobTXT.equals("") || passTXT.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Insert data into SQLite
                Boolean checkInsertData = DB.insertUserData(emailTXT, userTXT, dobTXT, passTXT);
                if(checkInsertData) {
                    Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                    // Navigate to Activity 2 (Sign In)
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}