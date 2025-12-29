package com.example.bookvault;

public class Book {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String year;

    // Empty constructor required for Firebase
    public Book() { }

    public Book(String id, String title, String author, String isbn, String year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getYear() { return year; }
}