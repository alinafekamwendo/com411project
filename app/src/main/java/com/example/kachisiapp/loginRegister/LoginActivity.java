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

public class LoginActivity extends AppCompatActivity {
  Button login_to_registerbtn,login_loginBtn;
  EditText userEmail,userPassword;
  AwesomeValidation awesomeValidation;
  FirebaseAuth firebaseAuth;
  ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialising variables
        userEmail=findViewById(R.id.login_email);
        userPassword=findViewById(R.id.login_password);
        login_loginBtn=findViewById(R.id.login_loginbuttonID);
        login_to_registerbtn =findViewById(R.id.login_registerbtn);
        progressBar=findViewById(R.id.login_progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        //VALIDATIONS
        awesomeValidation.addValidation(LoginActivity.this,R.id.login_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(LoginActivity.this,R.id.login_password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

        login_to_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });



        login_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                if (awesomeValidation.validate()) {

                    progressBar.setVisibility(View.VISIBLE);
                    //sugnup the user
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "logged in succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeDrawer.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Oops! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //closing progressbar
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });
    }

}

