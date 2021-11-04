package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class Menu0 extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu0);

        button = (Button) findViewById(R.id.scores);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu0.this, scores.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.options);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu0.this, Options.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu0.this, Play.class);
                startActivity(intent);
            }
        });


    }
}