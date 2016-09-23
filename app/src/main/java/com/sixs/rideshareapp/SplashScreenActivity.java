package com.sixs.rideshareapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sixs.rideshareapp.Util.SharedPreference;

public class SplashScreenActivity extends AppCompatActivity {

    String preferenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_view);

        TextView tx = (TextView)findViewById(R.id.tv_title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Roboto-BoldItalic.ttf");
        tx.setTypeface(custom_font);

        try {
            preferenceNumber  = SharedPreference.getMobileNumber(SplashScreenActivity.this, null);
            Log.v("TTT", "SharedPreference.getMobileNumber = " + SharedPreference.getMobileNumber(SplashScreenActivity.this, null));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(preferenceNumber != null && !preferenceNumber.isEmpty()) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                    SplashScreenActivity.this.finish();
                }
                else {
                    Intent myIntent = new Intent(SplashScreenActivity.this, EnterMobileNumberActivity.class);
                    SplashScreenActivity.this.startActivity(myIntent);
                    SplashScreenActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                    SplashScreenActivity.this.finish();
                }
            }
        }, 2000);
    }
}
