# Task Manager App 📝

A mobile application developed using **Flutter** and **Firebase Cloud Firestore** for real-time task management. This project was created for the **Mobile App Development (Assignment 4)** course.

## 🚀 Overview

The Task Manager App allows users to organize their daily activities efficiently. It supports **CRUD operations** (Create, Read, Update, Delete) and syncs data in real-time using Firebase.

## ✨ Features

* **Real-time Synchronization:** Tasks update instantly across devices.
* **Add Tasks:** Create new tasks with a title, description, and optional due date.
* **Update Tasks:** Edit task details or mark them as "Completed" (strikethrough effect).
* **Delete Tasks:** Remove tasks permanently from the database.
* **Status Tracking:** Toggle between Pending and Completed status.

## 📂 Project Structure

```text
lib/
├── main.dart                 # App entry point & Firebase initialization
├── home_screen.dart          # Displays the list of tasks (View/Delete/Toggle)
└── add_edit_task_screen.dart # Form to Add or Update tasks