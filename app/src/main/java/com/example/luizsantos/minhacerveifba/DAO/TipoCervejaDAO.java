package com.example.luizsantos.minhacerveifba.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luizsantos.minhacerveifba.Domain.TipoCerveja;

import java.util.ArrayList;
import java.util.List;

public class TipoCervejaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public TipoCervejaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public ArrayList<TipoCerveja> ObterTipos(){

        ArrayList<TipoCerveja> tipos = new ArrayList<>();

        Cursor cursor = banco.query("TipoCerveja", new String[]{"Id", "Nome", "Litragem"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            TipoCerveja tipo = new TipoCerveja();
            tipo.setId(cursor.getInt(0));
            tipo.setNome(cursor.getString(1));
            tipo.setLitragem(Integer.valueOf(cursor.getString(2)));

            tipos.add(tipo);
        }

        return tipos;
    }
}
