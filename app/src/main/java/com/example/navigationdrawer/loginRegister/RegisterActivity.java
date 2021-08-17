package com.example.navigationdrawer.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.HomeActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.home.HomeDrawer;

public class RegisterActivity extends AppCompatActivity {
        //declaring variables
        Button loginbtn,registerbtn;
        EditText firstname, surname, email, password;
        AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialising variables
        loginbtn=findViewById(R.id.register_login);
        registerbtn=findViewById(R.id.signup_register_button);
        firstname =findViewById(R.id.signup_firstname);
        surname =findViewById(R.id.signup_surname);
        email =findViewById(R.id.signup_email);
        password =findViewById(R.id.signup_password);
        
        //initializing validation style from awesome validation
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        //validations
        //for firstname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_firstname, RegexTemplate.NOT_EMPTY,R.string.invalid_firstname);
        //for surname
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_surname, RegexTemplate.NOT_EMPTY,R.string.invalid_surname);
        //email
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(RegisterActivity.this,R.id.signup_password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(awesomeValidation.validate()){
                Intent intent=new Intent(RegisterActivity.this, HomeDrawer.class);
                startActivity(intent);}
            }
        });


    }

}