package com.example.navigationdrawer.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_activity extends AppCompatActivity {
    ActivityOtpBinding activityOtpBinding;
    FirebaseAuth number_aunth;
    String verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOtpBinding=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(activityOtpBinding.getRoot());

        number_aunth=FirebaseAuth.getInstance();

        String phoneNumber=getIntent().getStringExtra("phone number");

        activityOtpBinding.numberIndicator.setText("Verify "+phoneNumber);

        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(number_aunth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(OTP_activity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        EditText code=findViewById(R.id.code_number);
                        String otp=code.getText().toString();
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationCode,otp);

                        number_aunth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(OTP_activity.this, "succsul", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(OTP_activity.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                                }
                        });

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OTP_activity.this, "failed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String verify, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verify, forceResendingToken);
                        verificationCode=verify;
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}