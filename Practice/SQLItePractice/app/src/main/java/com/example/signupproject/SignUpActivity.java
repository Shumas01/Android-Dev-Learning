package com.example.signupproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText email, username, dob, password;
    Button signup;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.signup_email);
        username = findViewById(R.id.signup_username);
        dob = findViewById(R.id.signup_dob);
        password = findViewById(R.id.signup_password);
        signup = findViewById(R.id.btn_signup);
        DB = new DBHandler(this);

        signup.setOnClickListener(v -> {
            String emailTXT = email.getText().toString();
            String userTXT = username.getText().toString();
            String dobTXT = dob.getText().toString();
            String passTXT = password.getText().toString();

            if(emailTXT.equals("") || userTXT.equals("") || dobTXT.equals("") || passTXT.equals("")) {
                Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkInsertData = DB.insertUserData(emailTXT, userTXT, dobTXT, passTXT);
                if(checkInsertData) {
                    Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                    // Go to Sign In Activity
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}