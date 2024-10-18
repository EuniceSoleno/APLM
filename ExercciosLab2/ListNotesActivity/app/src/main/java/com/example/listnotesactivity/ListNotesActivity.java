package com.example.listnotesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListNotesActivity extends AppCompatActivity {
    private ArrayList<Note> notes = new ArrayList<>();  // Changed to store Note objects
    private ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        ListView listView = findViewById(R.id.notesListView);
        Button newNoteButton = findViewById(R.id.newNoteButton);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);

        // ActivityResultLauncher to handle result from CreateNoteActivity
        ActivityResultLauncher<Intent> createNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Note newNote = (Note) result.getData().getSerializableExtra("note");
                        if (newNote != null) {
                            notes.add(newNote);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListNotesActivity.this, CreateNoteActivity.class);
            createNoteLauncher.launch(intent);
        });

        // ListView item click listener to open ReadNoteActivity with full note data
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListNotesActivity.this, ReadNoteActivity.class);
            intent.putExtra("note", notes.get(position));  // Pass the whole note object
            startActivity(intent);
        });
    }
}
