package com.example.pa8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nume,nr_disp,watts,nr_zile,nr_ore;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nume=findViewById(R.id.nume);
        nr_disp=findViewById(R.id.nr_disp);
        watts=findViewById(R.id.nr_watts);
        nr_zile=findViewById(R.id.nr_zile);
        nr_ore=findViewById(R.id.nr_ore);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(v -> {
            MyDatabase myDB=new MyDatabase(AddActivity.this);
            myDB.adaugareDispozitiv(nume.getText().toString().trim(),
                    Integer.valueOf(nr_disp.getText().toString().trim()),
                    Integer.valueOf(watts.getText().toString().trim()),
                    Integer.valueOf(nr_zile.getText().toString().trim()),
                    Integer.valueOf(nr_ore.getText().toString().trim()));
            Intent intent=new Intent(this,Dispozitive.class);
            startActivity(intent);
            finish();
        });




    }
}