package com.example.food_family;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    TextView login_signup, signup_btn;
    TextInputLayout name_var, username_var, password_var, email_var, mobile_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup);
        getSupportActionBar().hide();

        login_signup = findViewById(R.id.login_signup);
        login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        signup_btn = findViewById(R.id.signup_signup);

        name_var = findViewById(R.id.name_signup);
        username_var = findViewById(R.id.username_signup);
        password_var = findViewById(R.id.password_signup);
        email_var = findViewById(R.id.email_signup);
        mobile_var = findViewById(R.id.phone_signup);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_ = name_var.getEditText().getText().toString();
                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();
                String email_ = email_var.getEditText().getText().toString();
                String mobile_ = mobile_var.getEditText().getText().toString();

                if (!name_.isEmpty()) {
                    name_var.setError(null);
                    name_var.setErrorEnabled(false);
                    if (!username_.isEmpty()) {
                        username_var.setError(null);
                        username_var.setErrorEnabled(false);
                        if (!password_.isEmpty()) {
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if (!mobile_.isEmpty()) {
                                mobile_var.setError(null);
                                mobile_var.setErrorEnabled(false);
                                if (!email_.matches("[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                                    email_var.setError(null);
                                    email_var.setErrorEnabled(false);

                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    reference= firebaseDatabase.getReference("datauser");
                                    String name_s = name_var.getEditText().getText().toString();
                                    String username_s = username_var.getEditText().getText().toString();
                                    String password_s = password_var.getEditText().getText().toString();
                                    String email_s = email_var.getEditText().getText().toString();
                                    String mobile_s = mobile_var.getEditText().getText().toString();

                                    Storingdata storingdatas = new Storingdata(name_s, username_s, mobile_s, email_s, password_s);
                                    reference.child(username_s).setValue(storingdatas);

                                    Toast.makeText(Signup.this, "Register successfully !", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Signup.this, MainEnterance.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    email_var.setError("please Enter valid Email");
                                }

                            } else {
                                mobile_var.setError("Please Enter mobile Number");
                            }

                        } else {
                            password_var.setError("Please Enter The Password");
                        }

                    } else {
                        username_var.setError("Please Enter the Username");
                    }

                } else {
                    name_var.setError("Please Enter your Full Name");
                }

            }
        });


    }
}