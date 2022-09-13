package com.example.food_family;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button signup_btn, login_btn;
    TextInputLayout username_var, password_var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        signup_btn = findViewById(R.id.signup_login);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });

        username_var = findViewById(R.id.username_login);
        password_var = findViewById(R.id.password_login);

        login_btn = findViewById(R.id.login_login);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();


                if (!username_.isEmpty()){
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);   //To Remove set Eroor
                    if (!password_.isEmpty()){
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();// Assign as final because no any body hack
                        final String password_data = password_var.getEditText().getText().toString();//password final keyword

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");
                       Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String password_check = snapshot.child(username_data).child("password").getValue(String.class);
                                    if (password_check.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);

                                        Toast.makeText(login.this, "Login successfully !", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainEnterance.class);
                                        startActivity(intent);
                                        finish();



                                    }else{
                                        password_var.setError("Wrong Password");
                                    }
                                }else{
                                    username_var.setError("User dose not exist");
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }else{
                        password_var.setError("Please Enter The password"); // If User not fill Password
                    }
                }else{
                    username_var.setError("Please fill The Username"); // If user not fill UserName that code is run
                }

            }
        });




    }
}