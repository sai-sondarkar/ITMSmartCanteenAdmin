package edu.itm.smartcanteenadmin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.itm.smartcanteenadmin.R;

public class SplashActivity extends AppCompatActivity {

    //    protected boolean _active = true;
    protected int _splashTime = 3000; // time to display the splash screen in ms
    public boolean is_first = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while ((waited < _splashTime)) {
                        sleep(100);
//                        if (_active) {
                        waited += 100;
//                            if(waited>_splashTime-200)
//                            {
//
////                            }
//                        }
                    }
                } catch (Exception e) {

                } finally {                    {

                    startActivity(new Intent(SplashActivity.this,
                            LoginActivity.class));}

                    finish();
                }
            };
        };

        splashTread.start();

    }
}
