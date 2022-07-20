package com.example.classsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class new_user extends AppCompatActivity {
    EditText uName,uEmail,uAdd,uPh,uAge,uPass,uConfpass;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
//
        uName = (EditText)findViewById(R.id.user_name);
        uEmail = (EditText)findViewById(R.id.user_email);
        uAdd = (EditText)findViewById(R.id.user_Address);
        uAge = (EditText)findViewById(R.id.user_age);
        uPh = (EditText)findViewById(R.id.user_ph);
        uPass = (EditText)findViewById(R.id.user_pass);
        uConfpass = (EditText)findViewById(R.id.user_confirm_pass);
        rg = (RadioGroup) findViewById(R.id.radioGrp);
    }

    public void addUser(View view) {

         String name=uName.getText().toString().trim();
        String email = uEmail.getText().toString().trim();
        String address = uAdd.getText().toString().trim();
        String ph = uPh.getText().toString().trim();
        String age =uAge.getText().toString().trim();
        String pass = uPass.getText().toString().trim();
        String pass_confirm = uConfpass.getText().toString().trim();

        if(name.isEmpty()&&email.isEmpty()&&address.isEmpty()&&ph.isEmpty()&&age.isEmpty()&&pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all sections...",Toast.LENGTH_LONG).show();
        }
        else {

            if(pass.equals(pass_confirm)) {
                final  FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("user");

                ref.child(ph).child("name").setValue(name);
                ref.child(ph).child("phone").setValue(ph);
                ref.child(ph).child("email").setValue(email);
                ref.child(ph).child("address").setValue(address);
                ref.child(ph).child("age").setValue(age);
                ref.child(ph).child("pass").setValue(pass_confirm);

                //class created
                ref.child(ph).child("Classrooms").child("00000").child("ClassName").setValue("TEMP CLASS");
                ref.child(ph).child("Classrooms").child("00000").child("ClassPassCode").setValue("00000");

                //class created show
                HashMap<String,Object> mb = new HashMap<String,Object>();
                mb.put("ClassPassCode","00000");
                mb.put("ClassName","TEMP CLASS");
                ref.child(ph).child("ClassesCreated").push().setValue(mb);

                // class students
                ref.child(ph).child("Classrooms").child("00000").child("Students").push().child("Name").setValue("Jayesh Wani");
                ref.child(ph).child("Classrooms").child("00000").child("Students").push().child("Name").setValue("Sanket Rathod");

                // Assignment Info.
                String nameOfAssignment = "Assignment 0";
                String assignmentDesc = "Perform Practical First Given in Manual";

                HashMap<String,Object> m = new HashMap<String,Object>();
                m.put("assiName",nameOfAssignment);
                m.put("assiDesc",assignmentDesc);
                m.put("assiClassCode","CO123U");
                m.put("assiTeacherPh","123");
                ref.child(ph).child("Classrooms").child("00000").child("Assignments").child("AssignDesc").push().setValue(m);

                //Assignment Submission
                HashMap<String,Object> jb = new HashMap<String,Object>();
                jb.put("assiName",nameOfAssignment);
                jb.put("studentName","Temp");
                jb.put("pdfUrl","www.temp.com");
                ref.child(ph).child("Classrooms").child("00000").child("Assignments").child("AssignSubmittion").child(nameOfAssignment).push().setValue(jb);


                //classes join
                String pass1 = "00";
                ref.child(ph).child("MyClasses").child(pass1).child("passcode").setValue(pass1);
                ref.child(ph).child("MyClasses").child(pass1).child("ClassName").setValue("TEMP CLASS");
                ref.child(ph).child("MyClasses").child(pass1).child("TeacherPhoneNumber").setValue("99999");

                Toast.makeText(getApplicationContext(), "Your Registration Complete Successfully", Toast.LENGTH_LONG).show();
                uName.setText("");
                uEmail.setText("");
                uAdd.setText("");
                uAge.setText("");
                uPh.setText("");
                uPass.setText("");
                uConfpass.setText("");
                rg.clearCheck();

                Intent myIntent = new Intent(new_user.this, Login.class);
                startActivity(myIntent);
                finish();
            }
            else{
              Toast.makeText(getApplicationContext(),"Please Confirm your password",Toast.LENGTH_LONG).show();
            }
        }

    }
}