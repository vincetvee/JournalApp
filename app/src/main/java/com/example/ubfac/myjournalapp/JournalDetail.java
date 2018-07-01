package com.example.ubfac.myjournalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ubfac.myjournalapp.model.Journal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

public class JournalDetail extends AppCompatActivity {

    EditText date, time, title, content;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("myjournalapp");
    Journal journal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_detail);

        //unwrap the intent from the previous activity
        journal = Parcels.unwrap(getIntent().getParcelableExtra("key"));

        date= findViewById(R.id.date);
        date.setText(""+journal.getDate());

        time= findViewById(R.id.time);
        time.setText(""+ journal.getTime());

        title= findViewById(R.id.title);
        title.setText(""+journal.getTitle());

        content= findViewById(R.id.content);
        content.setText(""+journal.getContent());
    }

    public void save(View view) {
        ref.child(journal.getId()).setValue(new Journal(journal.getId(), title.getText().toString(), content.getText().toString(),
                Long.parseLong(date.getText().toString()), Long.parseLong(time.getText().toString())));
    }

    public void delete(View view) {
        ref.child(journal.getId()).setValue(null);
        finish();
    }
}
