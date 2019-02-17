package com.example.luizsantos.minhacerveifba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cerveja(id integer primary key autoincrement," +
                "nome varchar(50), " +
                "tipo varchar(10), " +
                "litragem varchar(10)," +
                "preco varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
