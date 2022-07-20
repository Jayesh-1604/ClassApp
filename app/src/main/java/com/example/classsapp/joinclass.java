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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class joinclass extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Intent myIntent;
    EditText jClassCode,jClassTeacherPh,jUserPhoneNumber,jClassStudentName;
    String shareUserName,shareUserPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinclass);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

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
                        myIntent = new Intent(joinclass.this, MainActivity.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_classes:
                        Toast.makeText(getApplicationContext(),"Welcome to classes",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(joinclass.this, classes.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_join_class:
                        Toast.makeText(getApplicationContext(),"Welcome To join class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_create_class:
                        Toast.makeText(getApplicationContext(),"Welcome to create class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(joinclass.this, createclass.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;


                    case R.id.nav_create_assignment:
                        Toast.makeText(getApplicationContext(),"Welcome to create Assignment",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(joinclass.this, addAssignment.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_FAQ:
                        Toast.makeText(getApplicationContext(),"Welcome to FAQ",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(joinclass.this, user_faq.class);
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
                        myIntent = new Intent(joinclass.this, Login.class);
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


        jClassCode = (EditText)findViewById(R.id.student_class_code);
        jClassTeacherPh = (EditText)findViewById(R.id.student_teacher_ph);
        jUserPhoneNumber = (EditText)findViewById(R.id.student_student_ph);
        jClassStudentName = (EditText)findViewById(R.id.student_name);

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

    public void joinClassRoom(View view) {
        String classCode = jClassCode.getText().toString().trim();
        String classTeacherPhone = jClassTeacherPh.getText().toString().trim();
        String classStudentPhone = jUserPhoneNumber.getText().toString().trim();
        String classStudentName = jClassStudentName.getText().toString().trim();

        if(classCode.isEmpty() && classTeacherPhone.isEmpty() && classTeacherPhone.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all sections...",Toast.LENGTH_LONG).show();
        }
        else {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
            Query checkUser = reference.orderByChild("phone").equalTo(classTeacherPhone);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        jClassTeacherPh.setError(null);
                        jClassTeacherPh.setEnabled(true);
                        String ClassCodeFromDB = null;
                        ClassCodeFromDB = dataSnapshot.child(classTeacherPhone).child("Classrooms").child(classCode).child("ClassPassCode").getValue(String.class);

                        String className = dataSnapshot.child(classTeacherPhone).child("Classrooms").child(classCode).child("ClassName").getValue(String.class);

                        if(ClassCodeFromDB.equals(classCode)){


                            // Add Class TO My Classes(Students Section)

                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("user");
                            ref.child(classStudentPhone).child("MyClasses").child(classCode);

                            //ClassPasscode
                            ref.child(classStudentPhone).child("MyClasses").child(classCode).child("passcode").setValue(classCode);
                            ref.child(classStudentPhone).child("MyClasses").child(classCode).child("ClassName").setValue(className);
                            ref.child(classStudentPhone).child("MyClasses").child(classCode).child("TeacherPhoneNumber").setValue(classTeacherPhone);

                            //Add Student to Class of Teacher

                            ref.child(classTeacherPhone).child("Classrooms").child(classCode).child("Students").push().child("Name").setValue(classStudentName);


                            Toast.makeText(getApplicationContext(), "Class Added Succsssfully"+ClassCodeFromDB+"=="+classCode, Toast.LENGTH_LONG).show();
                            jClassCode.setText("");
                            jClassTeacherPh.setText("");
                            jClassStudentName.setText("");
                            jUserPhoneNumber.setText("");
                            Intent myIntent = new Intent(joinclass.this, MainActivity.class);
                            myIntent.putExtra("name",shareUserName);
                            myIntent.putExtra("ph",shareUserPhone);
                            startActivity(myIntent);
                            finish();
                        }
                        else
                        {
                                    jClassCode.setError("Invalid Class Code");
                                    jClassCode.requestFocus();

                        }


                    }
                    else{

                        jClassTeacherPh.setError("Invalid Number");
                        jClassTeacherPh.requestFocus();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}