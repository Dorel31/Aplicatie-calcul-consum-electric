package com.example.pa8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

    }



    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView disp = (CardView) mainGrid.getChildAt(0);
            disp.setOnClickListener(view -> {

                Intent intent = new Intent(MainActivity.this,Dispozitive.class);

                startActivity(intent);

            });
            CardView consum=(CardView) mainGrid.getChildAt(1);
            consum.setOnClickListener(view ->{
                Intent intent=new Intent(MainActivity.this,Consumption.class);
                startActivity(intent);
            });

        }
    }
}