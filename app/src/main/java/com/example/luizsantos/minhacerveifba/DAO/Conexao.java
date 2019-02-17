package com.example.luizsantos.minhacerveifba.DAO;

import android.content.ContentValues;
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

        String table1 = "CREATE TABLE TipoCerveja(" +
                "Id INTEGER Primary Key autoincrement, " +
                "Nome VARCHAR(20)," +
                "Litragem INT);";
        String table2 = "CREATE TABLE Cerveja " +
                "(Id INTEGER Primary Key autoincrement, " +
                "Nome VARCHAR(50), " +
                "TipoId INTEGER, " +
                "Preco REAL, " +
                "FOREIGN KEY(TipoId) references TipoCerveja(Id));";

        String insert1 = "INSERT INTO TipoCerveja (Nome,Litragem) VALUES ('Latinha', 269);";
        String insert2 = "INSERT INTO TipoCerveja (Nome,Litragem) VALUES ('Latão', 473);";
        String insert3 = "INSERT INTO TipoCerveja (Nome,Litragem) VALUES ('Litro', 600);";
        String insert4 = "INSERT INTO TipoCerveja (Nome,Litragem) VALUES ('Latão', 1000);";



        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(insert1);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //insertTipoCerveja("Latinha", 269);
        //insertTipoCerveja("Latão", 473);
        //insertTipoCerveja("Litro", 600);
        //insertTipoCerveja("Latão", 1000);
    }

    private boolean insertTipoCerveja(String tipo, int litragem) {

        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("Nome", tipo);
            cv.put("Litragem", litragem);

            db.insert("TipoCerveja", null, cv);
            db.close();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }
}

