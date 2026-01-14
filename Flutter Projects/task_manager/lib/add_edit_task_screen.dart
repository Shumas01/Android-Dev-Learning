import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:intl/intl.dart';

class AddEditTaskScreen extends StatefulWidget {
  final String? taskId;
  final String? currentTitle;
  final String? currentDesc;
  final String? currentDate;

  const AddEditTaskScreen({
    super.key,
    this.taskId,
    this.currentTitle,
    this.currentDesc,
    this.currentDate,
  });

  @override
  State<AddEditTaskScreen> createState() => _AddEditTaskScreenState();
}

class _AddEditTaskScreenState extends State<AddEditTaskScreen> {
  final TextEditingController _titleController = TextEditingController();
  final TextEditingController _descController = TextEditingController();
  String? _selectedDate;

  @override
  void initState() {
    super.initState();
    // Pre-fill fields if we are Updating a task [cite: 20]
    if (widget.taskId != null) {
      _titleController.text = widget.currentTitle ?? '';
      _descController.text = widget.currentDesc ?? '';
      _selectedDate = widget.currentDate;
    }
  }

  // Function to save or update task to Firebase [cite: 18, 24]
  Future<void> _saveTask() async {
    final CollectionReference tasks =
    FirebaseFirestore.instance.collection('tasks');

    if (_titleController.text.isEmpty) return;

    if (widget.taskId == null) {
      // Add New Task
      await tasks.add({
        'title': _titleController.text,
        'description': _descController.text,
        'dueDate': _selectedDate,
        'isCompleted': false, // Default status [cite: 12]
      });
    } else {
      // Update Existing Task
      await tasks.doc(widget.taskId).update({
        'title': _titleController.text,
        'description': _descController.text,
        'dueDate': _selectedDate,
      });
    }

    if (mounted) Navigator.of(context).pop();
  }

  // Date Picker Function
  Future<void> _pickDate() async {
    DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2000),
      lastDate: DateTime(2100),
    );
    if (picked != null) {
      setState(() {
        _selectedDate = DateFormat('yyyy-MM-dd').format(picked);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.taskId == null ? 'Add Task' : 'Update Task'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            // Title Input [cite: 15]
            TextField(
              controller: _titleController,
              decoration: const InputDecoration(labelText: 'Task Title'),
            ),
            const SizedBox(height: 10),
            // Description Input [cite: 16]
            TextField(
              controller: _descController,
              decoration: const InputDecoration(labelText: 'Description'),
            ),
            const SizedBox(height: 10),
            // Due Date Selection [cite: 17]
            Row(
              children: [
                Text(_selectedDate == null
                    ? 'No Date Chosen'
                    : 'Due: $_selectedDate'),
                const Spacer(),
                TextButton(
                  onPressed: _pickDate,
                  child: const Text('Select Date'),
                )
              ],
            ),
            const SizedBox(height: 20),
            // Save Button [cite: 18]
            ElevatedButton(
              onPressed: _saveTask,
              child: Text(widget.taskId == null ? 'Add Task' : 'Update Task'),
            ),
          ],
        ),
      ),
    );
  }
}