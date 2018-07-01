package com.example.ubfac.myjournalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ubfac.myjournalapp.model.Journal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class JournalActivity extends AppCompatActivity implements JournalAdapter.ListItemClickListener {
    //    private DatabaseReference Database;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("myjournalapp");
    List<Journal> journalList = new ArrayList<>();
    private JournalAdapter mAdapter;
    private RecyclerView recyclerView;
    private Toast toast;

    int itemCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new JournalAdapter(this, this);
        recyclerView.setAdapter(mAdapter);

        readJournalfromDb();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(JournalActivity.this, NewJournalActivity.class);
                startActivity(intention);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journal, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemCount = 0;
        journalList.clear();
        readJournalfromDb();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_refresh:
               signOut();
               startActivity(new Intent(this, Login.class));
               finish();
               return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intention = new Intent(JournalActivity.this, JournalDetail.class);
        intention.putExtra("key", Parcels.wrap(journalList.get(clickedItemIndex)));
        startActivity(intention);

    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void readJournalfromDb() {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String key) {
                // Get Post object and use the values to update the UI
                itemCount++;
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("TAG", "changed ");
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String key) {
                // Get Post object and use the values to update the UI
                Log.e("TAG", "added ");
                Journal journal = dataSnapshot.getValue(Journal.class);
                journalList.add(journal);
                if(itemCount== journalList.size()){
                    mAdapter.setData(journalList);
                }
//                for(DataSnapshot)
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("TAG", "changed ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

//    private void getAllJournal(DataSnapshot dataSnapshot) {
//        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
//            String journalTitle = singleSnapshot.getValue(String.class);
//            allJournal.add(new Journal(journalTitle));
//            mAdapter = new mAdapter(JournalActivity.this, allJournal);
//            RecyclerView.setAdapter(mAdapter);
//        }
//    }

}



