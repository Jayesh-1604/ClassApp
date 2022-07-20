package com.example.classsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class createclass extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Intent myIntent;
    String shareUserName,shareUserPhone;

    EditText cClassName,cClassTeacherPh,cClassPassCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createclass);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        cClassName = (EditText) findViewById(R.id.create_class_name);
        cClassPassCode = (EditText) findViewById(R.id.create_class_code);
        cClassTeacherPh = (EditText) findViewById(R.id.create_teacher_ph_number);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_myclass:
                        Toast.makeText(getApplicationContext(),"Welcome to My Class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, MainActivity.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_classes:
                        Toast.makeText(getApplicationContext(),"Welcome to classes",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, classes.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_join_class:
                        Toast.makeText(getApplicationContext(),"Welcome To join class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, joinclass.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;
                    case R.id.nav_create_class:
                        Toast.makeText(getApplicationContext(),"Welcome to create class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_create_assignment:
                        Toast.makeText(getApplicationContext(),"Welcome to create Assignment",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, addAssignment.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_FAQ:
                        Toast.makeText(getApplicationContext(),"Welcome to FAQ",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, user_faq.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_contact:
                        Toast.makeText(getApplicationContext(),"Contact",Toast.LENGTH_LONG).show();
                        String add[] = {"gcoejclassrooms@gmail.com"};
                        String sub="Don't Worry "+"....\n";
                        sub+="Just Send your Query Message on\n\n gcoejclassrooms@gmail.com\n\n";
                        sub+="Our Technical Assistant will Contact Your Soon....#StayConnected";
                        sub+="\n\n\nTake care \nStay Safe \n#Go Corona";
                        composeEmail(add,"Class App Feedback",sub);
                        break;


                    case R.id.nav_signout:
                        Toast.makeText(getApplicationContext(),"Signout",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(createclass.this, Login.class);
                        startActivity(myIntent);
                        finish();
                        break;
                }
                return true;
            }
        });

        Intent intent = getIntent();
        shareUserName = intent.getStringExtra("name");
        shareUserPhone = intent.getStringExtra("ph");


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

    public void createClass(View view) {
        String className = cClassName.getText().toString().trim();
        String classPassCode = cClassPassCode.getText().toString().trim();
        String classTeacherPhone = cClassTeacherPh.getText().toString().trim();

        if(className.isEmpty() && classPassCode.isEmpty() && classTeacherPhone.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all sections...",Toast.LENGTH_LONG).show();
        }
        else {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
                Query checkUser = reference.orderByChild("phone").equalTo(classTeacherPhone);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            cClassTeacherPh.setError(null);
                            cClassTeacherPh.setEnabled(true);

                            String PhoneFromDB = dataSnapshot.child(classTeacherPhone).child("phone").getValue(String.class);
                            if(PhoneFromDB.equals(classTeacherPhone)){
                                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference ref = database.getReference("user");
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode);

                                // class Name added
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("ClassName").setValue(className);

                                //class passcode added
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("ClassPassCode").setValue(classPassCode);

                                //class students added
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("Students").push().child("Name").setValue("Jayesh Wani");
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("Students").push().child("Name").setValue("Sanket Rathod");

                                // Assignment Name
                                String nameOfAssignment = "Assignment 0";
                                String assignmentDesc = "Perform Practical First Given in Manual";
                                HashMap<String,Object> m = new HashMap<String,Object>();
                                m.put("assiName",nameOfAssignment);
                                m.put("assiDesc",assignmentDesc);
                                m.put("assiClassCode",classPassCode);
                                m.put("assiTeacherPh",classTeacherPhone);

                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("Assignments").child("AssignDesc").push().setValue(m);

                                //Assignment Submission
                                HashMap<String,Object> jb = new HashMap<String,Object>();
                                jb.put("assiName",nameOfAssignment);
                                jb.put("studentName","Temp");
                                jb.put("pdfUrl","www.temp.com");
                                ref.child(classTeacherPhone).child("Classrooms").child(classPassCode).child("Assignments").child("AssignSubmittion").child(nameOfAssignment).push().setValue(jb);

                                //class seen to teacher
                                HashMap<String,Object> zl = new HashMap<String,Object>();
                                zl.put("ClassPassCode",classPassCode);
                                zl.put("ClassName",className);
                                ref.child(classTeacherPhone).child("ClassesCreated").push().setValue(zl);


                                Toast.makeText(getApplicationContext(), "Class Added Succsssfully", Toast.LENGTH_LONG).show();
                                cClassTeacherPh.setText("");
                                cClassPassCode.setText("");
                                cClassName.setText("");
                                Intent myIntent = new Intent(createclass.this, classes.class);
                                myIntent.putExtra("name",shareUserName);
                                myIntent.putExtra("ph",shareUserPhone);
                                startActivity(myIntent);
                                finish();
                            }
                        }
                        else{
                            cClassTeacherPh.setError("Invalid Phone Number");
                            cClassTeacherPh.requestFocus();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }

    }
