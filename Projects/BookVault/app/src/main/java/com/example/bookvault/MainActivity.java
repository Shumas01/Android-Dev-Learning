package com.example.bookvault;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> list;
    private DatabaseReference dbBooks;
    private FirebaseAuth auth;
    private Button btnAddBook, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            // 1. Tell Firebase to forget the user
            auth.signOut();

            // 2. Send user back to Login Screen
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Close Main Activity
        });

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // 1. Initialize Realtime Database
        // NEW CODE (Unique for each user)
        String userId = auth.getCurrentUser().getUid(); // Get the unique ID (e.g., "AbCd123...")
        dbBooks = FirebaseDatabase.getInstance().getReference("books").child(userId);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddBook = findViewById(R.id.btnAddBook);

        // Optional: Add a logout/delete account button ID to your activity_main.xml if desired
        // btnLogout = findViewById(R.id.btnLogout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        // 2. Setup Adapter with Click Listener
        adapter = new BookAdapter(list, this::showUpdateDeleteDialog);
        recyclerView.setAdapter(adapter);

        // 3. Load Data from Realtime Database
        dbBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Book book = data.getValue(Book.class);
                    list.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Add Book Button Action
        btnAddBook.setOnClickListener(v -> showBookDialog(null));
    }

    // Helper: Show Dialog to Add or Update Book
    private void showBookDialog(Book bookToUpdate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_book, null);
        builder.setView(view);

        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etAuthor = view.findViewById(R.id.etAuthor);
        EditText etIsbn = view.findViewById(R.id.etIsbn);
        EditText etYear = view.findViewById(R.id.etYear);

        if (bookToUpdate != null) {
            etTitle.setText(bookToUpdate.getTitle());
            etAuthor.setText(bookToUpdate.getAuthor());
            etIsbn.setText(bookToUpdate.getIsbn());
            etYear.setText(bookToUpdate.getYear());
        }

        builder.setPositiveButton(bookToUpdate == null ? "Add" : "Update", (dialog, which) -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String isbn = etIsbn.getText().toString().trim();
            String year = etYear.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Title and Author required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (bookToUpdate == null) {
                String id = dbBooks.push().getKey();
                Book book = new Book(id, title, author, isbn, year);
                dbBooks.child(id).setValue(book);
            } else {
                Book book = new Book(bookToUpdate.getId(), title, author, isbn, year);
                dbBooks.child(bookToUpdate.getId()).setValue(book);
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void showUpdateDeleteDialog(Book book) {
        new AlertDialog.Builder(this)
                .setTitle("Options")
                .setMessage("Choose an action for: " + book.getTitle())
                .setPositiveButton("Edit", (dialog, which) -> showBookDialog(book))
                .setNegativeButton("Delete", (dialog, which) -> {
                    dbBooks.child(book.getId()).removeValue();
                    Toast.makeText(this, "Book Deleted", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancel", null)
                .show();
    }
}