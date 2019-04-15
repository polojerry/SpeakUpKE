package com.polotechnologies.speakupke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = findViewById(R.id.login_button);
        mSignUp = findViewById(R.id.sign_up_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(openMainActivity);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(openSignUpActivity);
            }
        });

    }
}
