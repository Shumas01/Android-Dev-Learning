package com.example.signupproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetActivity extends AppCompatActivity {
    EditText email, newPass;
    Button update;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = findViewById(R.id.reset_email);
        newPass = findViewById(R.id.reset_new_pass);
        update = findViewById(R.id.btn_reset_confirm);
        DB = new DBHandler(this);

        update.setOnClickListener(v -> {
            String emailTXT = email.getText().toString();
            String passTXT = newPass.getText().toString();

            Boolean checkUpdate = DB.updatePassword(emailTXT, passTXT);
            if(checkUpdate) {
                Toast.makeText(ResetActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ResetActivity.this, "Email Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}