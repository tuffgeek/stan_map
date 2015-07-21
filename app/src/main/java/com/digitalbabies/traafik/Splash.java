package com.digitalbabies.traafik;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


/**
 * Created by ankit on 15/7/15.
 */
public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;


    private SharedPreferences preferences;
    private SharedPreferences.Editor toEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        preferences = getSharedPreferences(Myprefs.MyPreferences, MODE_PRIVATE);
        toEdit = preferences.edit();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash.this,
                        Login.class);
                startActivity(mainIntent);
                overridePendingTransition(0, 0);
                finish();
                Log.d("TAG", "nooooooooo");

            }
        },SPLASH_DISPLAY_LENGTH);



    }
}

