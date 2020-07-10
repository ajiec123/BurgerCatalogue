package com.example.gallery;

import androidx.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnsignIn, btnsignUp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnsignIn = findViewById(R.id.btn_signIn);
        btnsignUp = findViewById(R.id.btn_signUp);

        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtUsername.getText().toString(),
                        edtPassword.getText().toString());
            }
        });

        btnsignUp.setOnClickListener(new View.OnClickListener() { // onclick to register page (mainactivity)
            @Override
            public void onClick(View view) {
                Intent toRegisterPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toRegisterPage);
            }
        });


    }

    private void signIn(final String username, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()) {
                    if(!username.isEmpty()) {
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "You're successfully login", Toast.LENGTH_SHORT).show();
                            Intent toBurgerActivity = new Intent(getApplicationContext(), BurgerActivity.class);
                            startActivity(toBurgerActivity);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password is wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Username is not registered", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}