package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class assignmentGiven extends AppCompatActivity {
    String teacher_your_assi_class_name, teacher_your_assi_user_class_pass,teacher_your_assi_classTeacher_ph;

    RecyclerView teacher_your_assi_recvieww;
    yourClassAssignmentGivenAdapter adapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_given);


        Intent intent = getIntent();
        teacher_your_assi_class_name = intent.getStringExtra("className");
        teacher_your_assi_user_class_pass = intent.getStringExtra("classPassCode");
        teacher_your_assi_classTeacher_ph = MainActivity.shareUserPhoneToShare;



        teacher_your_assi_recvieww=(RecyclerView)findViewById(R.id.myclasses_assignmentGiven_recview);
        teacher_your_assi_recvieww.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<yourClassAssignmentModel> options =
                new FirebaseRecyclerOptions.Builder<yourClassAssignmentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user").child(teacher_your_assi_classTeacher_ph).child("Classrooms").child(teacher_your_assi_user_class_pass).child("Assignments").child("AssignDesc"),yourClassAssignmentModel.class)
                        .build();

        adapterr= new yourClassAssignmentGivenAdapter(options,getApplicationContext());
        teacher_your_assi_recvieww.setAdapter(adapterr);
        Toast.makeText(getApplicationContext(),"Your Assignment",Toast.LENGTH_LONG).show();

    }






    public void gotoTeacherclasses(View view) {
        Intent myIntent = new Intent(assignmentGiven.this, classes.class);
        myIntent.putExtra("name","**");
        myIntent.putExtra("ph",MainActivity.shareUserPhoneToShare);
        startActivity(myIntent);
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
}



