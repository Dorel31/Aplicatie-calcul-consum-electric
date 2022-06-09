package com.example.pa8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Consumption extends AppCompatActivity {


    AnyChartView anyChartView;
    MyDatabase myDB;
    ArrayList<String> id,nume,nr_disp,watt,nr_zile,nr_ore;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        anyChartView=findViewById(R.id.chart);
        myDB=new MyDatabase(Consumption.this);
        id=new ArrayList<>();
        nume=new ArrayList<>();
        nr_disp=new ArrayList<>();
        watt=new ArrayList<>();
        nr_zile=new ArrayList<>();
        nr_ore=new ArrayList<>();

        stocareDateInArray();

        textView= findViewById(R.id.afisConsum);
        float x=calculConsum();

        x=x/1000;
        float y= (float) (x*1.5);
        double roundOff = (double) Math.round(y * 100) / 100;
        String z=String.valueOf(x)+" KW/h"+" ≅ "+String.valueOf(roundOff)+" Ron";
        textView.setText(z);
        System.out.println(id.size());

        creareChart();

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
            Toast.makeText(this, "Nu exista dispozitive", Toast.LENGTH_SHORT).show();
        }
        else
        {


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

    int calculConsum()
    {
        int c=0;
        for(int i=0;i<id.size();i++)
        {
            c=c+(Integer.parseInt(nr_disp.get(i))*Integer.parseInt(watt.get(i))*Integer.parseInt(nr_zile.get(i))*Integer.parseInt(nr_ore.get(i)));
        }
        return c;
    }

    void creareChart()
    {
        Pie pie= AnyChart.pie();
        List<DataEntry> dataEntries =new ArrayList<>();
        for(int i=0;i<id.size();i++)
        {
            int x=Integer.parseInt(nr_disp.get(i))*Integer.parseInt(watt.get(i))*Integer.parseInt(nr_zile.get(i))*Integer.parseInt(nr_ore.get(i));
            float y= (float) x/1000;
            dataEntries.add(new ValueDataEntry(nume.get(i),y));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }

}