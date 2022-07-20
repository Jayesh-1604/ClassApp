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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class user_faq extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Intent myIntent;
    String shareUserName,shareUserPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_faq);

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
                        myIntent = new Intent(user_faq.this, MainActivity.class);
                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_classes:
                        Toast.makeText(getApplicationContext(),"Welcome to classes",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(user_faq.this, classes.class);

                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_join_class:
                        Toast.makeText(getApplicationContext(),"Welcome To join class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(user_faq.this, joinclass.class);

                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;
                    case R.id.nav_create_class:
                        Toast.makeText(getApplicationContext(),"Welcome to create class",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(user_faq.this, createclass.class);

                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;

                    case R.id.nav_create_assignment:
                        Toast.makeText(getApplicationContext(),"Welcome to create Assignment",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        myIntent = new Intent(user_faq.this, addAssignment.class);

                        myIntent.putExtra("name",shareUserName);
                        myIntent.putExtra("ph",shareUserPhone);
                        startActivity(myIntent);
                        finish();
                        break;
                    case R.id.nav_FAQ:
                        Toast.makeText(getApplicationContext(),"Welcome to FAQ",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
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
                        myIntent = new Intent(user_faq.this, Login.class);
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

}