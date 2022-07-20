package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class studentAssignmentSubmittion extends AppCompatActivity {
    TextView submittion_assi_classcode,submittion_assi_title,submittion_assi_DESC,submittion_assi_teacher_ph;
    EditText studentTextEmail;
    String sub_class_code,sub_assi_title,sub_assi_DESC,sub_assi_teacher_ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assignment_submittion);

        submittion_assi_title=(TextView)findViewById(R.id.submittion_Assignment_Title);
        submittion_assi_classcode=(TextView)findViewById(R.id.submittion_assignment_classpasscode);
        submittion_assi_teacher_ph=(TextView)findViewById(R.id.submition_class_teacher_ph);
        submittion_assi_DESC=(TextView)findViewById(R.id.submittion_Assignment_desc);

        studentTextEmail=(EditText)findViewById(R.id.student_assign_sub_email);



        Intent intent = getIntent();
        sub_assi_title = intent.getStringExtra("AssignmentName");
        sub_assi_DESC = intent.getStringExtra("AssignmentDescription");
        sub_assi_teacher_ph = intent.getStringExtra("teacher_ph");
        sub_class_code = intent.getStringExtra("ClassCodee");

        submittion_assi_title.setText("TITLE := "+sub_assi_title);
        submittion_assi_classcode.setText("Class Code := "+sub_class_code);
        submittion_assi_DESC.setText("Description := "+sub_assi_DESC);
        submittion_assi_teacher_ph.setText("Teacher Ph := "+sub_assi_teacher_ph);


    }


    public void suubmitAssignment(View view) {

        String student_email =studentTextEmail.getText().toString().trim();
       if(student_email.isEmpty()) {
           Toast.makeText(getApplicationContext(), "Please Enter your Email", Toast.LENGTH_LONG).show();
       }
       else {
           Intent myIntent = new Intent(studentAssignmentSubmittion.this, submitAssignment.class);
           myIntent.putExtra("title", sub_assi_title);
           myIntent.putExtra("code", sub_class_code);
           myIntent.putExtra("desc", sub_assi_DESC);
           myIntent.putExtra("ph", sub_assi_teacher_ph);
           myIntent.putExtra("email", student_email);
           startActivity(myIntent);
       }
    }

    public void submittion_assign_gotoclass(View view) {
        Intent myIntent = new Intent(studentAssignmentSubmittion.this, Assignments.class);
        myIntent.putExtra("className","**");
        myIntent.putExtra("classPassCode",sub_class_code);
        myIntent.putExtra("classTeacherPh",sub_assi_teacher_ph);
        startActivity(myIntent);
        finish();
    }
}