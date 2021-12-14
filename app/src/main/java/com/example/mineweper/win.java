package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class win extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        button = (Button) findViewById(R.id.mainMenu);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(win.this, Menu0.class);
            startActivity(intent);
        });
    }
}