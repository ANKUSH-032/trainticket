

package com.sms.trainticketbook.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.sms.trainticketbook.MainActivity;
import com.sms.trainticketbook.R;
import com.sms.trainticketbook.project.DataWareHouse.RegisteratoinLab;
import com.sms.trainticketbook.project.DataWareHouse.SPManipulation;
import com.sms.trainticketbook.project.DataWareHouse.UserDetails;


public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
    TextView textView;
    Animation enterAnim;
    SPManipulation manipulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tcontact);
        manipulation = SPManipulation.getInstance(this);
        enterAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.zoom_in);
        textView.startAnimation(enterAnim);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                if (manipulation.getIsUserlogin() && !manipulation.getUSERID().equalsIgnoreCase("NA")) {
                    UserDetails userdetails = RegisteratoinLab.get(SplashActivity.this).findUserDetails(Integer.parseInt(manipulation.getUSERID()));
                    if (userdetails != null) {
                        Intent selectPageIntent = new Intent(SplashActivity.this, SelectPage.class);
                        selectPageIntent.putExtra("userDlog", userdetails);
                        startActivity(selectPageIntent);
                    }
                } else {
                    Intent i = new Intent(SplashActivity.this, HomePage.class);
                    startActivity(i);
                }


                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
