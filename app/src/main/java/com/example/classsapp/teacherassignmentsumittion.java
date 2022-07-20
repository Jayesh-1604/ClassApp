package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class teacherassignmentsumittion extends AppCompatActivity {
    String teacher_assi_sub_classCode , teacher_assi_sub_classTeacherPh , teacher_assi_sub_AssiName;
    public static String TeacherClassCode,TeacherAssignemntName;
    RecyclerView teacher_your_assi_sub_recvieww;
    teacherAssiSubmittionAdapter adapterrrr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherassignmentsumittion);
        Intent intent = getIntent();
        teacher_assi_sub_AssiName = intent.getStringExtra("AssignmentName");
        teacher_assi_sub_classCode = intent.getStringExtra("ClassCodee");
        teacher_assi_sub_classTeacherPh = intent.getStringExtra("teacher_ph");
        TeacherClassCode = teacher_assi_sub_classCode;
        TeacherAssignemntName = teacher_assi_sub_AssiName;



        teacher_your_assi_sub_recvieww=(RecyclerView)findViewById(R.id.myclasses_assignmentSubmittion_recview);
        teacher_your_assi_sub_recvieww.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<teacherAssiSubmittionModel> optionssss =
                new FirebaseRecyclerOptions.Builder<teacherAssiSubmittionModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user").child(teacher_assi_sub_classTeacherPh).child("Classrooms").child(teacher_assi_sub_classCode).child("Assignments").child("AssignSubmittion").child(teacher_assi_sub_AssiName),teacherAssiSubmittionModel.class)
                        .build();

        adapterrrr= new teacherAssiSubmittionAdapter(optionssss,getApplicationContext());
        teacher_your_assi_sub_recvieww.setAdapter(adapterrrr);
        Toast.makeText(getApplicationContext(),"Your Assignment",Toast.LENGTH_LONG).show();




        Toast.makeText(getApplicationContext(),teacher_assi_sub_AssiName+"::"+teacher_assi_sub_classCode+"::"+teacher_assi_sub_classTeacherPh,Toast.LENGTH_LONG).show();
    }

    public void gotoTeacherAssiclasses(View view) {
        Intent myIntent = new Intent(teacherassignmentsumittion.this, classes.class);
        myIntent.putExtra("name","**");
        myIntent.putExtra("ph",MainActivity.shareUserPhoneToShare);
        startActivity(myIntent);
        finish();

    }


    protected void onStart() {
        super.onStart();
        adapterrrr.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterrrr.stopListening();
    }
}