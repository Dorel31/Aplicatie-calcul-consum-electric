package com.example.pa8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dispozitive extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView imaginegol;
    TextView textgol;

    MyDatabase myDB;
    ArrayList<String> id,nume,nr_disp,watt,nr_zile,nr_ore;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispozitive);

        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button1);
        imaginegol=findViewById(R.id.imaginegol);
        textgol=findViewById(R.id.textgol);
        add_button.setOnClickListener(v -> {
                Intent intent=new Intent(Dispozitive.this, AddActivity.class);
                startActivity(intent);

        });

        myDB=new MyDatabase(Dispozitive.this);
        id=new ArrayList<>();
        nume=new ArrayList<>();
        nr_disp=new ArrayList<>();
        watt=new ArrayList<>();
        nr_zile=new ArrayList<>();
        nr_ore=new ArrayList<>();

        stocareDateInArray();

        customAdapter=new CustomAdapter(Dispozitive.this,this,id,nume,nr_disp,watt,nr_zile,nr_ore);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Dispozitive.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            recreate();
    }

    void stocareDateInArray()
    {
        Cursor cursor=myDB.citire();
        if(cursor.getCount()==0) {
            imaginegol.setVisibility(View.VISIBLE);
            textgol.setVisibility(View.VISIBLE);
        }
        else
        {
            imaginegol.setVisibility(View.GONE);
            textgol.setVisibility(View.GONE);

            while(cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                nume.add(cursor.getString(1));
                nr_disp.add(cursor.getString(2));
                watt.add(cursor.getString(3));
                nr_zile.add(cursor.getString(4));
                nr_ore.add(cursor.getString(5));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.meniu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all)
            confirmaStergereTotala();
        return super.onOptionsItemSelected(item);
    }

    void confirmaStergereTotala()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Dispozitive.this);
        builder.setTitle("Stergeti tot?");
        builder.setMessage("Doriti sa stergeti toate datele?");
        builder.setPositiveButton("Da", (dialog, which) -> {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            MyDatabase myDB=new MyDatabase(this);
            myDB.stergereTotala();
            Intent intent=new Intent(Dispozitive.this,Dispozitive.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Nu", (dialog, which) -> {

        });
        builder.create().show();
    }

}