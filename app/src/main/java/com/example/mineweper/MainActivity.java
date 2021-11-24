//Splash Screen
package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Sound effect
                player = MediaPlayer.create(MainActivity.this, R.raw.scramble);
                player.start();

                Intent intent = new Intent(MainActivity.this, Menu0.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}