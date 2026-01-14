import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'add_edit_task_screen.dart'; // Import the screen for adding/editing

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  // 1. Reference to the Firebase 'tasks' collection
  final CollectionReference _tasksCollection =
  FirebaseFirestore.instance.collection('tasks');

  // 2. Function to delete a task from Firebase
  Future<void> _deleteTask(String id) async {
    await _tasksCollection.doc(id).delete();

    // Show a small popup message
    if (mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Task deleted successfully')));
    }
  }

  // 3. Function to toggle 'Pending' vs 'Completed'
  Future<void> _toggleTaskStatus(String id, bool currentStatus) async {
    await _tasksCollection.doc(id).update({'isCompleted': !currentStatus});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Task Manager')),

      // 4. Floating Action Button to Add a New Task
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const AddEditTaskScreen()),
          );
        },
        child: const Icon(Icons.add),
      ),

      // 5. StreamBuilder for Real-Time Updates
      body: StreamBuilder(
        stream: _tasksCollection.snapshots(), // Listening to Firebase
        builder: (context, AsyncSnapshot<QuerySnapshot> streamSnapshot) {

          // Check if data has arrived
          if (streamSnapshot.hasData) {
            return ListView.builder(
              itemCount: streamSnapshot.data!.docs.length,
              itemBuilder: (context, index) {
                final DocumentSnapshot documentSnapshot =
                streamSnapshot.data!.docs[index];

                return Card(
                  margin: const EdgeInsets.all(10),
                  child: ListTile(
                    // A. Task Title (Strike-through if completed)
                    title: Text(
                      documentSnapshot['title'],
                      style: TextStyle(
                        decoration: documentSnapshot['isCompleted']
                            ? TextDecoration.lineThrough
                            : TextDecoration.none,
                      ),
                    ),

                    // B. Description & Due Date
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(documentSnapshot['description']),
                        if (documentSnapshot['dueDate'] != null)
                          Text(
                            "Due: ${documentSnapshot['dueDate']}",
                            style: const TextStyle(color: Colors.red, fontSize: 12),
                          ),
                      ],
                    ),

                    // C. Checkbox to mark as Completed
                    leading: Checkbox(
                      value: documentSnapshot['isCompleted'],
                      onChanged: (bool? value) {
                        _toggleTaskStatus(
                            documentSnapshot.id, documentSnapshot['isCompleted']);
                      },
                    ),

                    // D. Edit and Delete Buttons
                    trailing: SizedBox(
                      width: 100,
                      child: Row(
                        children: [
                          // Edit Icon
                          IconButton(
                            icon: const Icon(Icons.edit),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => AddEditTaskScreen(
                                    taskId: documentSnapshot.id,
                                    currentTitle: documentSnapshot['title'],
                                    currentDesc: documentSnapshot['description'],
                                    currentDate: documentSnapshot['dueDate'],
                                  ),
                                ),
                              );
                            },
                          ),
                          // Delete Icon
                          IconButton(
                            icon: const Icon(Icons.delete, color: Colors.red),
                            onPressed: () => _deleteTask(documentSnapshot.id),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              },
            );
          }
          // Show loading spinner while waiting for Firebase
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}