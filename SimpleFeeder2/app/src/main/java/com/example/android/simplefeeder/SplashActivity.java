package com.example.android.simplefeeder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new android.os.Handler().postDelayed(new Runnable(){
            public void run(){
                android.content.Intent HomeIntent = new  android.content.Intent(SplashActivity.this,MainActivity.class);
                startActivity(HomeIntent);
<<<<<<< HEAD
                finish();
                }
                },
                SPLASH_TIME_OUT
        );
    }
}
=======

                finish();

            }
            },
                SPLASH_TIME_OUT
        );
    }
}
>>>>>>> 5973ff4dfbb3b73116a5fccc143f3bce88729dfe
