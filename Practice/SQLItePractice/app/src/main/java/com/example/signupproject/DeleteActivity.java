package com.example.signupproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    EditText email;
    Button delete;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        email = findViewById(R.id.delete_email);
        delete = findViewById(R.id.btn_delete_confirm);
        DB = new DBHandler(this);

        delete.setOnClickListener(v -> {
            String emailTXT = email.getText().toString();
            Boolean checkDelete = DB.deleteData(emailTXT);
            if(checkDelete) {
                Toast.makeText(DeleteActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DeleteActivity.this, "Account Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}