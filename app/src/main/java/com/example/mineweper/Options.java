package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class Options extends AppCompatActivity {
    public Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        button = (Button) findViewById(R.id.mainMenu);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, Menu0.class);
                startActivity(intent);
            }
        });
    }
}