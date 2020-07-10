package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtUsername, edtPassword;
    Button btnsignUp, btnsignIn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnsignUp = findViewById(R.id.btn_signUp); // Sign-up
        btnsignIn = findViewById(R.id.btn_signIn); // sign-in

        btnsignIn.setOnClickListener(new View.OnClickListener() { // onclick to login page (loginactivity)
            @Override
            public void onClick(View view) {
                Intent toLoginPage = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(toLoginPage);
            }
        });

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User();

                user.setName(edtName.getText().toString().trim()); // name
                user.setEmail(edtEmail.getText().toString().trim()); // email
                user.setUsername(edtUsername.getText().toString().trim()); // username
                user.setPassword(edtPassword.getText().toString().trim()); // password

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { // get data
                        if(dataSnapshot.child(user.getUsername()).exists()) { // check data if exists or not
                            Toast.makeText(MainActivity.this, "Username is already exists!", Toast.LENGTH_SHORT).show();
                        } else {
                            users.child(user.getUsername()).setValue(user); // insert registration data
                            Toast.makeText(MainActivity.this, "Registration Success.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // blablah
                    }
                });

            }
        });
    }
}