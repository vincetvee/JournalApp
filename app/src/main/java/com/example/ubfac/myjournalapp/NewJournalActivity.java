package com.example.ubfac.myjournalapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ubfac.myjournalapp.model.Journal;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewJournalActivity extends AppCompatActivity {
    EditText date, time, title, content;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("myjournalapp");

    List<Journal> journalList = new ArrayList<>();
    Journal journal;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.new_journal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date= findViewById(R.id.date);
        time= findViewById(R.id.time);
        title= findViewById(R.id.title);
        content= findViewById(R.id.content);

    }

    public void save(View v) {

        //saving journal with  random key
        DatabaseReference uniqueRef = ref.push();
        Journal journal = new Journal(uniqueRef.getKey(),
                title.getText().toString(),content.getText().toString(),
                Long.parseLong(date.getText().toString()),
                Long.parseLong(time.getText().toString()));
        uniqueRef.setValue(journal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Log.e("TAG", "Data not saved successfully. " + databaseError.getMessage());
                    Toast.makeText(NewJournalActivity.this, " Note could not be saved ", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("TAG", "Data saved successfully. ");
                    Toast.makeText(NewJournalActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }

}
