package com.polotechnologies.speakupke;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    Button mSignUp;

    TextInputEditText mPhoneNumber;
    TextInputEditText mPassword;

    final int RC_SIGN_UP = 1001;
    final int RC_SIGN_IN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = findViewById(R.id.login_button);
        mSignUp = findViewById(R.id.sign_up_button);
        mPhoneNumber = findViewById(R.id.login_mobile_number);
        mPassword= findViewById(R.id.login_password);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signIn();
                getUserValues();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void getUserValues() {

        if (Objects.requireNonNull(mPhoneNumber.getText()).toString().isEmpty()){
            mPhoneNumber.setError("Required");
            return;
        }
        if (Objects.requireNonNull(mPassword.getText()).toString().isEmpty()){
            mPassword.setError("Required");
            return;
        }

        String userNumber = mPhoneNumber.getText().toString().trim();
        String userPassword = mPassword.getText().toString().trim();

    }



    private void signUp() {

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_UP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_UP) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent openSignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                openSignUpActivity.putExtra("uId", user.getUid());
                startActivity(openSignUpActivity);
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

                if (response == null){
                    Toast.makeText(this, "Cancelled by User", Toast.LENGTH_SHORT).show();
                }else{

                    switch (response.getError().getErrorCode()){
                        case ErrorCodes.NO_NETWORK:
                            Toast.makeText(this, "No Network Available", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        }

    }
}
