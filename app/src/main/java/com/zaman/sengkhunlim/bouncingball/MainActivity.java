package com.zaman.sengkhunlim.bouncingball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BouncingBall bouncingBall = findViewById( R.id.bouncing_ball_view );
        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                bouncingBall.doAnimation();
            }

        });
        thread.start();

    }

}
