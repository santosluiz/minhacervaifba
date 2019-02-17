package com.example.luizsantos.minhacerveifba.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.luizsantos.minhacerveifba.Domain.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class CervejaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public CervejaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long Inserir(Cerveja cerveja){
        ContentValues values = new ContentValues();
        values.put("Nome", cerveja.getNome());
        values.put("TipoId", cerveja.getTipoId());
        values.put("Preco", cerveja.getPreco());

        return banco.insert("Cerveja", null, values);
    }

    public List<Cerveja> ObterCervejas(){
        List<Cerveja> cerveja = new ArrayList<>();
        Cursor cursor = banco.query("Cerveja", new String[]{"Id", "Nome", "TipoId", "Preco"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Cerveja cerva = new Cerveja();
            cerva.setId(cursor.getInt(0));
            cerva.setNome(cursor.getString(1));
            cerva.setTipoId(cursor.getInt(2));
            cerva.setPreco(cursor.getFloat(3));
            cerveja.add(cerva);
        }

        return cerveja;
    }

    public void Excluir(Cerveja c){
        banco.delete("Cerveja", "id = ?", new String[]{ String.valueOf(c.getId()) });
    }

    public void Atualizar(Cerveja cerveja){

        ContentValues values = new ContentValues();
        values.put("Nome", cerveja.getNome());
        values.put("TipoId", cerveja.getTipoId());
        values.put("Preco", cerveja.getPreco());

        banco.update("Cerveja", values, "id = ?", new String[]{ String.valueOf(cerveja.getId()) });
    }
}
