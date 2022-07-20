package com.example.classsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText lphone,lpassword;
    String EmailFromDB;
    String NameFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lphone = (EditText)findViewById(R.id.login_number);
        lpassword = (EditText)findViewById(R.id.login_password);


    }

    public void loginhere(View view) {

        String phoneNo = lphone.getText().toString().trim();
        String password = lpassword.getText().toString().trim();
        if(phoneNo.isEmpty()&&password.isEmpty()){

            Toast.makeText(getApplicationContext(), "Enter Phone Number and Password", Toast.LENGTH_LONG).show();
        }else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
            Query checkUser = reference.orderByChild("phone").equalTo(phoneNo);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        lphone.setError(null);
                        lphone.setEnabled(true);

                        String PasswordFromDB = dataSnapshot.child(phoneNo).child("pass").getValue(String.class);
                        if(PasswordFromDB.equals(password)){
                            lphone.setError(null);
                            lphone.setEnabled(true);
                            NameFromDB = dataSnapshot.child(phoneNo).child("name").getValue(String.class);
                            String AgeFromDB = dataSnapshot.child(phoneNo).child("age").getValue(String.class);
                            String AddressFromDB = dataSnapshot.child(phoneNo).child("address").getValue(String.class);
                            EmailFromDB = dataSnapshot.child(phoneNo).child("email").getValue(String.class);
                            String PhoneNoFromDB = dataSnapshot.child(phoneNo).child("phone").getValue(String.class);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("name",NameFromDB);
                            intent.putExtra("ph",PhoneNoFromDB);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Welcome "+NameFromDB, Toast.LENGTH_LONG).show();
                            finish();

                        }
                        else{
                            lpassword.setError("Invalid Password");
                            lpassword.requestFocus();
                        }
                    }
                    else{
                        lphone.setError("No Such User Exist");
                        lphone.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    public void registeredYourself(View view){
        startActivity(new Intent(this,new_user.class));
    }
    public void forgot_pass(View view)
    {


        String add[] = {"gcoejclassrooms@gmail.com"};
        String sub="Don't Worry "+"User"+"....\n";
        sub+="Just Send this Message To\n\n gcoejclassrooms@gmail.com\n\n";
        sub+="We will send your Password On your  registered  email Id....\n\n";
        sub+="Just Enter Your <Name> <Phone Number> <Email Id > Here\n";
        sub+="\n\n\nTake care \nStay Safe \n#Go Corona";
        composeEmail(add,"Class App Forgot Password",sub);
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
