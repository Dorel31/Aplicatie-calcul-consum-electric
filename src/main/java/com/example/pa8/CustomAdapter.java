package com.example.pa8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id,nume,nr_disp,watt,nr_zile,nr_ore;

    Animation translate_anim;

    CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList nume, ArrayList nr_disp, ArrayList watt, ArrayList nr_zile, ArrayList nr_ore)
    {
        this.activity=activity;
        this.context=context;
        this.id=id;
        this.nume=nume;
        this.nr_disp=nr_disp;
        this.watt=watt;
        this.nr_zile=nr_zile;
        this.nr_ore=nr_ore;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        //holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.nume_txt.setText(String.valueOf(nume.get(position)));
        holder.watt_txt.setText(String.valueOf(watt.get(position)));
        holder.nr_disp_txt.setText(String.valueOf(nr_disp.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent=new Intent(context, UpdateActivity.class);
            intent.putExtra("id",String.valueOf(id.get(position)));
            intent.putExtra("nume",String.valueOf(nume.get(position)));
            intent.putExtra("nr_disp",String.valueOf(nr_disp.get(position)));
            intent.putExtra("watt",String.valueOf(watt.get(position)));
            intent.putExtra("nr_zile",String.valueOf(nr_zile.get(position)));
            intent.putExtra("nr_ore",String.valueOf(nr_ore.get(position)));
            activity.startActivityForResult(intent,1);
        });
    }

    @Override
    public int getItemCount()
    {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt,nume_txt,watt_txt,nr_disp_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //id_txt=itemView.findViewById(R.id.disp_id);
            nume_txt=itemView.findViewById(R.id.nume);
            watt_txt=itemView.findViewById(R.id.watt);
            nr_disp_txt=itemView.findViewById(R.id.nr_disp);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            translate_anim.setDuration(1000);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
