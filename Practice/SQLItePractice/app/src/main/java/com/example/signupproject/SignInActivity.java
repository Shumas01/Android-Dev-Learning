package com.example.signupproject;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;
    Button signin, deleteBtn, resetBtn;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.signin_username);
        password = findViewById(R.id.signin_password);
        signin = findViewById(R.id.btn_signin);
        deleteBtn = findViewById(R.id.btn_goto_delete);
        resetBtn = findViewById(R.id.btn_goto_reset);
        DB = new DBHandler(this);

        signin.setOnClickListener(v -> {
            String userTXT = username.getText().toString();
            String passTXT = password.getText().toString();

            if(userTXT.equals("") || passTXT.equals("")) {
                Toast.makeText(SignInActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserPass = DB.checkUsernamePassword(userTXT, passTXT);
                if(checkUserPass) {
                    Toast.makeText(SignInActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Go to Delete Activity
        deleteBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
            startActivity(intent);
        });

        // Go to Reset Password Activity
        resetBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
            startActivity(intent);
        });
    }
}