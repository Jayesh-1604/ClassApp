package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class studentAssiDashboard extends AppCompatActivity {

    String student_email,student_name,student_pdf_url;
    EditText Tmakrs , Tgrade , Tremarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assi_dashboard);

        Intent intent = getIntent();
        student_name = intent.getStringExtra("student_name");
        student_email = intent.getStringExtra("student_email");
        student_pdf_url = intent.getStringExtra("pdfUrl");
        Toast.makeText(getApplicationContext(),student_name+":"+student_email,Toast.LENGTH_LONG).show();

        Tmakrs = (EditText) findViewById(R.id.teacher_assign_sub_marks);
        Tremarks = (EditText) findViewById(R.id.teacher_assign_sub_remarks);
        Tgrade = (EditText) findViewById(R.id.teacher_assign_sub_grade);


    }

    public void BackToAssignmentsSubmittion(View view) {
        Intent myIntent = new Intent(studentAssiDashboard.this, teacherassignmentsumittion.class);
        myIntent.putExtra("AssignmentName",teacherassignmentsumittion.TeacherAssignemntName);
        myIntent.putExtra("teacher_ph",MainActivity.shareUserPhoneToShare);
        myIntent.putExtra("ClassCodee",teacherassignmentsumittion.TeacherClassCode);
        startActivity(myIntent);
        finish();
    }

    public void FeedbackToAssignment(View view) {
        String studentMarks = Tmakrs.getText().toString();
        String studentRemakrs = Tremarks.getText().toString();
        String studentGrade = Tgrade.getText().toString();
        String add[] = {student_email};
        String sub="Dear "+student_name+"....\n";
        sub+="Your Result Of "+teacherassignmentsumittion.TeacherAssignemntName+" : "+teacherassignmentsumittion.TeacherClassCode+" is\n";
        sub+="Marks :- "+studentMarks+"\n";
        sub+="Grade :- "+studentGrade+"\n";
        sub+="Remak :- "+studentRemakrs+"\n";
        sub+="\n\n\nTake care \nStay Safe \n#Go Corona";
        composeEmail(add,"Results",sub);
    }


    public void ViewAssignment(View view) {

        Intent myIntent = new Intent(studentAssiDashboard.this, viewPdf.class);
        myIntent.putExtra("pdfUrl",student_pdf_url);
        startActivity(myIntent);

    }


    public void composeEmail(String[] addresses, String subject,String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}