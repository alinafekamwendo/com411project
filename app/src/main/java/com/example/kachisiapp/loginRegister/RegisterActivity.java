package com.example.kachisiapp.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
        //declaring variables
        Button loginbtn,registerbtn;
        EditText userFirstname, surname, userEmail, userPassword,phoneNumber;
        AwesomeValidation awesomeValidation;
        ProgressBar progressBar;
        FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialising variables
        loginbtn=findViewById(R.id.register_login);
        registerbtn=findViewById(R.id.signup_register_button);
        userFirstname =findViewById(R.id.signup_firstname);
        surname =findViewById(R.id.signup_surname);
        userEmail =findViewById(R.id.signup_email);
        userPassword =findViewById(R.id.signup_password);
        phoneNumber=findViewById(R.id.signup_phoneNumber);
        progressBar=findViewById(R.id.signup_progressBar);
        //firebase instance
        firebaseAuth=FirebaseAuth.getInstance();
        //initializing validation style from awesome validation
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        //validations
        //for userFirstname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_firstname, RegexTemplate.NOT_EMPTY,R.string.invalid_firstname);
        //for surname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_surname, RegexTemplate.NOT_EMPTY,R.string.invalid_surname);
        //userEmail
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

        //checking user exixtence in the data base
        if(firebaseAuth.getCurrentUser() !=null){
            Toast.makeText(getApplicationContext(), "seems the user exists", Toast.LENGTH_SHORT).show();
           finish();
       }
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=userEmail.getText().toString();
                String password=userPassword.getText().toString();


                progressBar.setVisibility(View.VISIBLE);

                //registering the user
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "user registered succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeDrawer.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Oops!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

    }

}