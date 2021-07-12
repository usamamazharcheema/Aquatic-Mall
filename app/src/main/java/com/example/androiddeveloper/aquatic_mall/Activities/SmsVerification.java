package com.example.androiddeveloper.aquatic_mall.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SmsVerification extends AppCompatActivity {
EditText phonumber,code;
String verification_code;
FirebaseAuth auth;
PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);
        phonumber=findViewById(R.id.phonenumber);
        code=findViewById(R.id.verificationcode);
        auth=FirebaseAuth.getInstance();
        mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verification_code=s;
                Toast.makeText(SmsVerification.this, "Code has been sent", Toast.LENGTH_SHORT).show();
                super.onCodeSent(s, forceResendingToken);
            }
        };

    }
    public void sensms(View v)
    {
      String  phone=phonumber.getText().toString();
      PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60, TimeUnit.SECONDS,this,mCallback);
    }
    public  void signedinwithphone(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SmsVerification.this, "User signded in succ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SmsVerification.this, "User signded fali", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void verify(View v)
    {
        String inpu_code=code.getText().toString();
        if(verification_code.equals(""))
        {
            verifyphone(verification_code,inpu_code);
        }
        else
        {
            verifyphone(verification_code,inpu_code);
        }

    }
    public void verifyphone(String verifycode, String inputcode)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifycode,inputcode);
        signedinwithphone(credential);
    }
}
