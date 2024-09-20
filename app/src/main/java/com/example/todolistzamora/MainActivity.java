package com.example.todolistzamora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskList;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.addButton);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);


        addButton.setOnClickListener(view -> addTask());


        listView.setOnItemClickListener((parent, view, position, id) -> editTask(position));
    }


    private void addTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Task");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String taskText = input.getText().toString();
            if (!taskText.isEmpty()) {
                Task newTask = new Task(taskText);
                taskList.add(newTask);
                adapter.add(newTask.getTaskName());
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }


    private void editTask(int position) {
        Task task = taskList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(task.getTaskName());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newTaskName = input.getText().toString();
            if (!newTaskName.isEmpty()) {
                task.setTaskName(newTaskName);
                adapter.remove(adapter.getItem(position));
                adapter.insert(newTaskName, position);
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}

