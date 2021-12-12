package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class Options extends AppCompatActivity {
    public Button button;
    Button btnplay, btnstop;
    android.media.MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        button = (Button) findViewById(R.id.mainMenu);

        //sound test buttons
        btnplay = findViewById(R.id.startSound);
        btnstop = findViewById(R.id.stopSound);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) {

                    player = MediaPlayer.create(Options.this, R.raw.test);

                }
                player.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {

                    player.stop();

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, Menu0.class);
                startActivity(intent);
            }
        });
    }
}