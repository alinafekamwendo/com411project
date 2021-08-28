package com.example.kachisiapp.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
        //declaring variables
        Button loginbtn,registerbtn;
        EditText userFirstname, userSurname, userEmail, userPassword,phoneNumber;
        AwesomeValidation awesomeValidation;
        ProgressBar progressBar;
        FirebaseAuth firebaseAuth;
        FirebaseFirestore firebaseFirestore;
        String userId;
        Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialising variables
        loginbtn=findViewById(R.id.register_login);
        registerbtn=findViewById(R.id.signup_register_button);
        userFirstname =findViewById(R.id.signup_firstname);
        userSurname =findViewById(R.id.signup_surname);
        userEmail =findViewById(R.id.signup_email);
        userPassword =findViewById(R.id.signup_password);
        phoneNumber=findViewById(R.id.signup_phoneNumber);
        category =findViewById(R.id.signup_spinner_id);
        progressBar=findViewById(R.id.signup_progressBar);

        //firebase instance for aunthentication
        firebaseAuth=FirebaseAuth.getInstance();
        //firestore instance
        firebaseFirestore=FirebaseFirestore.getInstance();

        //initializing validation style from awesome validation
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        //validations
        //for userFirstname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_firstname, RegexTemplate.NOT_EMPTY,R.string.invalid_firstname);
        //for userSurname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_surname, RegexTemplate.NOT_EMPTY,R.string.invalid_surname);
        //for phone number
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_phoneNumber, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
        //userEmail
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

        //clicking login button in case user exists should take him to the login window
      loginbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
          }
      });

        //clicking register button will register email and the given password in firebase
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String firstname=userFirstname.getText().toString();
                String surname=userSurname.getText().toString();
                String phone=phoneNumber.getText().toString();
                String memberCategory=category.getSelectedItem().toString();

                //unless the entered data is validated user cannot be registered
                if (awesomeValidation.validate()) {

                progressBar.setVisibility(View.VISIBLE);

                //registering the user
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "user registered succesfully", Toast.LENGTH_SHORT).show();
                            //get the id of this just created user
                            userId= firebaseAuth.getCurrentUser().getUid();

                            //creating document instance to store our members data,passing the id to the document
                            DocumentReference registeredUsers=firebaseFirestore.collection("members").document(email);
                            Map<String,Object> member=new HashMap<>();
                            member.put("firstname",firstname);
                            member.put("surname",surname);
                            member.put("email",email);
                            member.put("phoneNumber",phone);
                            member.put("category",memberCategory);
                            registeredUsers.set(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(RegisterActivity.this, "membership created", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Error! "+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });


                            startActivity(new Intent(getApplicationContext(), HomeDrawer.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Oops! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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