package com.example.pa8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="ProiectPA.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="dispozitivele_mele";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="nume_dispozitiv";
    private static final String COLUMN_QUANTITY="nr_dispozitive";
    private static final String COLUMN_WATTS="nr_watti";
    private static final String COLUMN_DAYS="nr_zile";
    private static final String COLUMN_TIME="nr_ore";



    MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+
                        COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME+" TEXT, "+
                        COLUMN_QUANTITY+" INTEGER, "+
                        COLUMN_WATTS+" INTEGER, "+
                        COLUMN_DAYS+" INTEGER, "+
                        COLUMN_TIME+" INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    void adaugareDispozitiv(String nume,int nr_disp,int watt,int nr_zile,int nr_ore)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,nume);
        cv.put(COLUMN_QUANTITY,nr_disp);
        cv.put(COLUMN_WATTS,watt);
        cv.put(COLUMN_DAYS,nr_zile);
        cv.put(COLUMN_TIME,nr_ore);

        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)
            Toast.makeText(context, "Adaugare esuata", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Adaugare cu succes", Toast.LENGTH_SHORT).show();

    }

    Cursor citire()
    {
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    void modificare(String id,String nume, String nr_disp, String watt, String nr_zile, String nr_ore)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,nume);
        cv.put(COLUMN_QUANTITY,nr_disp);
        cv.put(COLUMN_WATTS,watt);
        cv.put(COLUMN_DAYS,nr_zile);
        cv.put(COLUMN_TIME,nr_ore);
        long result=db.update(TABLE_NAME,cv,"id=?", new String[]{id});
        if(result==-1)
        {
            Toast.makeText(context, "Esuare la modificare", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Modificare cu succes", Toast.LENGTH_SHORT).show();
    }

    void stergereRand(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"id=?",new String[]{id});
        if(result==-1)
            Toast.makeText(context, "Esuare la stergere", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Sters cu succes", Toast.LENGTH_SHORT).show();
    }

    void stergereTotala()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }

//    int calculareConsum() {
//        String query = "SELECT SUM( " + COLUMN_QUANTITY + "*" + COLUMN_WATTS + "*" + COLUMN_DAYS + "*" + COLUMN_TIME + " ) FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(query, null);
//        int consum=-1;
//        if (c.moveToFirst()) {
//            consum = c.getInt(0);
//        }
//        c.close();
//        return consum;
//    }

}
