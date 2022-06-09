package com.example.pa8;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nume,watt,nr_disp,nr_zile,nr_ore;
    Button update_button,delete_button;
    String id1,nume1,watt1,nr_disp1,nr_zile1,nr_ore1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nume=findViewById(R.id.nume2);
        watt=findViewById(R.id.nr_watts2);
        nr_disp=findViewById(R.id.nr_disp2);
        nr_zile=findViewById(R.id.nr_zile2);
        nr_ore=findViewById(R.id.nr_ore2);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar ab=getSupportActionBar();
        if(ab!=null)
            ab.setTitle(nume1);

        update_button.setOnClickListener(v -> {
            MyDatabase myDB=new MyDatabase(UpdateActivity.this);
            nume1=nume.getText().toString().trim();
            nr_disp1=nr_disp.getText().toString().trim();
            watt1=watt.getText().toString().trim();
            nr_zile1=nr_zile.getText().toString().trim();
            nr_ore1=nr_ore.getText().toString().trim();
            myDB.modificare(id1,nume1,nr_disp1,watt1,nr_zile1,nr_ore1);
        });

        delete_button.setOnClickListener(v -> confirmaStergere());
    }

    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nume") && getIntent().hasExtra("watt") &&
                getIntent().hasExtra("nr_disp") && getIntent().hasExtra("nr_zile") && getIntent().hasExtra("nr_ore"))
        {
            id1=getIntent().getStringExtra("id");
            nume1=getIntent().getStringExtra("nume");
            nr_disp1=getIntent().getStringExtra("nr_disp");
            watt1=getIntent().getStringExtra("watt");
            nr_zile1=getIntent().getStringExtra("nr_zile");
            nr_ore1=getIntent().getStringExtra("nr_ore");

            nume.setText(nume1);
            nr_disp.setText(nr_disp1);
            watt.setText(watt1);
            nr_zile.setText(nr_zile1);
            nr_ore.setText(nr_ore1);

        }
        else
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmaStergere()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Stergeti "+nume1+ " ?");
        builder.setMessage("Doriti sa stergeti "+nume1+" ?");
        builder.setPositiveButton("Da", (dialog, which) -> {
            MyDatabase myDB=new MyDatabase(UpdateActivity.this);
            myDB.stergereRand(id1);
            finish();
        });
        builder.setNegativeButton("Nu", (dialog, which) -> {

        });
        builder.create().show();
    }

}