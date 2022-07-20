package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;

public class Assignments extends AppCompatActivity {

    String class_name, user_class_pass,classTeacher_ph;

    RecyclerView recvieww;
    studentsAssignmentAdapter adapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        Intent intent = getIntent();
        class_name = intent.getStringExtra("className");
        user_class_pass = intent.getStringExtra("classPassCode");
        classTeacher_ph = intent.getStringExtra("classTeacherPh");



        recvieww=(RecyclerView)findViewById(R.id.recview_assignment);
        recvieww.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<studentAssignmentModel> options =
                new FirebaseRecyclerOptions.Builder<studentAssignmentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user").child(classTeacher_ph).child("Classrooms").child(user_class_pass).child("Assignments").child("AssignDesc"),studentAssignmentModel.class)
                        .build();

        adapterr= new studentsAssignmentAdapter(options,getApplicationContext());
        recvieww.setAdapter(adapterr);
        Toast.makeText(getApplicationContext(),"Welcome to Assignment",Toast.LENGTH_LONG).show();

    }







    public void gotoclass(View view) {


        Intent intent = new Intent(Assignments.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name","**");
        intent.putExtra("ph",MainActivity.shareUserPhoneToShare);



        startActivity(intent);
        finish();

    }

    protected void onStart() {
        super.onStart();
        adapterr.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterr.stopListening();
    }

    public void submitAss(View view) {
    }
}
