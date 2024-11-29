package com.sms.trainticketbook.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sms.trainticketbook.R;
import com.sms.trainticketbook.project.DataWareHouse.SPManipulation;

public class HomePage extends AppCompatActivity {

    private Button loginBtn, creditBtn;
    public Intent loginPageIntent, registerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loginBtn = (Button) findViewById(R.id.login_Button);
        creditBtn = (Button) findViewById(R.id.credits_Button);

        loginPageIntent = new Intent(this, LoginPage.class);
        registerIntent = new Intent(this, RegisterPage.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginPageIntent);
                finish();
            }
        });
        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerIntent);
                finish();
            }
        });


    }
}
