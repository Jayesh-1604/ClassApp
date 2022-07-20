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

        import java.util.HashMap;


public class addAssignment extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Intent myIntent;
    String shareUserName,shareUserPhone;
    EditText adClassCode,adClassTeacherPh,adAssignTitle,adAssignDescrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

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
                        myIntent = new Intent(addAssignment.this, MainActivity.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_classes:
                        Toast.makeText(getApplicationContext(),"Welcome to classes",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(addAssignment.this, classes.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_join_class:
                        Toast.makeText(getApplicationContext(),"Welcome To join class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(addAssignment.this, joinclass.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;
                    case R.id.nav_create_class:
                        Toast.makeText(getApplicationContext(),"Welcome to create class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(addAssignment.this, createclass.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_create_assignment:
                        Toast.makeText(getApplicationContext(),"Welcome to create Assignment",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_FAQ:
                        Toast.makeText(getApplicationContext(),"Welcome to FAQ",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(addAssignment.this, user_faq.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_contact:
                        Toast.makeText(getApplicationContext(),"Contact",Toast.LENGTH_LONG).show();
                        String add[] = {"gcoejclassrooms@gmail.com"};
                        String sub="Don't Worry "+"....\n";
                        sub+="Just Send your Query Message on\n\n  gcoejclassrooms@gmail.com\n\n";
                        sub+="Our Technical Assistant will Contact Your Soon....#StayConnected";
                        sub+="\n\n\nTake care \nStay Safe \n#Go Corona";
                        composeEmail(add,"Class App Feedback",sub);
                        break;


                    case R.id.nav_signout:
                        Toast.makeText(getApplicationContext(),"Signout",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(addAssignment.this, Login.class);
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


        adClassCode = (EditText)findViewById(R.id.assign_class_code);
        adClassTeacherPh = (EditText)findViewById(R.id.assign_class_tacher_ph);
        adAssignTitle = (EditText)findViewById(R.id.assign_class_assignment_name);
        adAssignDescrip = (EditText)findViewById(R.id.assign_class_assignment_desc);

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

    public void createAssignment(View view) {
        String assignClassCode = adClassCode.getText().toString().trim();
        String assignClassTeacherPhone = adClassTeacherPh.getText().toString().trim();
        String assignTitle = adAssignTitle.getText().toString().trim();
        String assignDesc = adAssignDescrip.getText().toString().trim();

        if(assignClassCode.isEmpty() && assignClassTeacherPhone.isEmpty() && assignTitle.isEmpty() && assignDesc.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all sections...",Toast.LENGTH_LONG).show();
        }
        else {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
            Query checkUser = reference.orderByChild("phone").equalTo(assignClassTeacherPhone);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        adClassTeacherPh.setError(null);
                        adClassTeacherPh.setEnabled(true);
                        String ClassCodeFromDB = null;
                        ClassCodeFromDB = dataSnapshot.child(assignClassTeacherPhone).child("Classrooms").child(assignClassCode).child("ClassPassCode").getValue(String.class);

                        //String className = dataSnapshot.child(classTeacherPhone).child("Classrooms").child(classCode).child("ClassName").getValue(String.class);

                        if(ClassCodeFromDB.equals(assignClassCode)){


                            // Add Assignment TO Assignment Description and Assignment Submittion

                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("user");

                            // Add Assignment To  Assignments ---> AssignDesc


                            HashMap<String,Object> assAdd = new HashMap<String,Object>();
                            assAdd.put("assiClassCode",assignClassCode);
                            assAdd.put("assiTeacherPh",assignClassTeacherPhone);
                            assAdd.put("assiName",assignTitle);
                            assAdd.put("assiDesc",assignDesc);
                            ref.child(assignClassTeacherPhone).child("Classrooms").child(assignClassCode).child("Assignments").child("AssignDesc").push().setValue(assAdd);


                            //Add Assignement to AssignSubmittion
                            HashMap<String,Object> assSub = new HashMap<String,Object>();

                            assSub.put("assiName",assignTitle);
                            assSub.put("pdfUrl","Google.com");
                            assSub.put("studentName","Temp");
                            ref.child(assignClassTeacherPhone).child("Classrooms").child(assignClassCode).child("Assignments").child("AssignSubmittion").child(assignTitle).push().setValue(assSub);


                            Toast.makeText(getApplicationContext(), "Assignment Added Succsssfully", Toast.LENGTH_LONG).show();
                            adClassTeacherPh.setText("");
                            adClassCode.setText("");
                            adAssignTitle.setText("");
                            adAssignDescrip.setText("");
                            Intent myIntent = new Intent(addAssignment.this, classes.class);
                            myIntent.putExtra("name",shareUserName);
                            myIntent.putExtra("ph",shareUserPhone);
                            startActivity(myIntent);
                            finish();


                        }
                        else
                        {
                            adClassCode.setError("Invalid Class Code");
                            adClassCode.requestFocus();

                        }


                    }
                    else{

                        adClassTeacherPh.setError("Invalid Number");
                        adClassTeacherPh.requestFocus();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}