package com.polotechnologies.speakupke;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polotechnologies.speakupke.DataModels.UserSignUp;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText mUsername;
    TextInputEditText mPassword;
    TextInputEditText mConfirmPassword;
    Button mSignUpButton;
    ProgressBar mSignUpProgress;

    String uID;

    //Database Reference
    DatabaseReference mDatabaseReference;

    //Firebase Authentication
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = findViewById(R.id.username_sign_up);
        mPassword = findViewById(R.id.password_sign_up);
        mConfirmPassword = findViewById(R.id.confirm_password_sign_up);
        mSignUpButton = findViewById(R.id.sign_up_button);
        mSignUpProgress = findViewById(R.id.signUpProgressBar);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        uID = intent.getStringExtra("uId");

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserValues();
            }
        });

    }

    private void getUserValues() {

        if (Objects.requireNonNull(mUsername.getText()).toString().isEmpty()){
            mUsername.setError("Required");
        }
        if (Objects.requireNonNull(mPassword.getText()).toString().isEmpty()){
            mPassword.setError("Required");
        }
        if (Objects.requireNonNull(mConfirmPassword.getText()).toString().isEmpty()){
            mConfirmPassword.setError("Required");
        }

        String username = mUsername.getText().toString().trim();
        String userPass = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();
        
        if (!userPass.equals(confirmPassword)){
            Toast.makeText(this, "Password do not Match", Toast.LENGTH_SHORT).show();
            mPassword.setText("");
            mConfirmPassword.setText("");
            mPassword.requestFocus();
        }else{
            mSignUpProgress.setVisibility(View.VISIBLE);
            signUp(username,userPass);
        }
        
    }

    private void signUp(String username, String userPass) {
        String id = mDatabaseReference.push().getKey();
        UserSignUp newUserSignUP = new UserSignUp(
                uID,
                userPass,
                username
        );

        assert id != null;
        mDatabaseReference.child(id).setValue(newUserSignUP).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mSignUpProgress.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                Intent openLoginActivity = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(openLoginActivity);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mSignUpProgress.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "Failed To Sign Up", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
