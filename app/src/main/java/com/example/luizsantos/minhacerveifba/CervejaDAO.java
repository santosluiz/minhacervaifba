package com.example.luizsantos.minhacerveifba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CervejaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public CervejaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Cerveja cerveja){
        ContentValues values = new ContentValues();
        values.put("nome", cerveja.getNome());
        values.put("tipo", cerveja.getTipo());
        values.put("litragem", cerveja.getLitragem());
        values.put("preco", cerveja.getPreco());

        return banco.insert("cerveja", null, values);
    }

    public List<Cerveja> obterTodos(){
        List<Cerveja> cerveja = new ArrayList<>();
        Cursor cursor = banco.query("cerveja", new String[]{"id", "nome", "tipo", "litragem", "preco"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Cerveja cerva = new Cerveja();
            cerva.setId(cursor.getInt(0));
            cerva.setNome(cursor.getString(1));
            cerva.setTipo(cursor.getString(2));
            cerva.setLitragem(cursor.getString(3));
            cerva.setPreco(cursor.getString(4));
            cerveja.add(cerva);
        }

        return cerveja;
    }

    public void excluir(Cerveja c){
        banco.delete("cerveja", "id = ?", new String[]{c.getId().toString()});
    }

    public void atualizar(Cerveja cerveja){
        ContentValues values = new ContentValues();
        values.put("nome", cerveja.getNome());
        values.put("tipo", cerveja.getTipo());
        values.put("litragem", cerveja.getLitragem());
        values.put("preco", cerveja.getPreco());

        banco.update("cerveja", values, "id = ?", new String[]{cerveja.getId().toString()});
    }
}
