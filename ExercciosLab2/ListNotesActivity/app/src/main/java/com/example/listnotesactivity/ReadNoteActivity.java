package com.example.listnotesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadNoteActivity extends AppCompatActivity {
    private TextView noteTitleTextView;
    private TextView noteContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        noteTitleTextView = findViewById(R.id.noteTitleTextView);
        noteContentTextView = findViewById(R.id.noteContentTextView);  // New TextView for the note content

        // Receive the Note object via Intent
        Intent intent = getIntent();
        Note note = (Note) intent.getSerializableExtra("note");

        if (note != null) {
            noteTitleTextView.setText(note.getTitle());
            noteContentTextView.setText(note.getContent());
        } else {
            noteTitleTextView.setText("No Title");
            noteContentTextView.setText("No Content");
        }
    }
}
