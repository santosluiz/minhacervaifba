package com.example.luizsantos.minhacerveifba.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.luizsantos.minhacerveifba.Modelo.Cerveja;
import com.example.luizsantos.minhacerveifba.Modelo.CervejaLista;

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

//    public List<Cerveja> ObterCervejas(){
//        List<Cerveja> cerveja = new ArrayList<>();
//        Cursor cursor = banco.query("Cerveja", new String[]{"Id", "Nome", "TipoId", "Preco"},
//                null, null, null, null, null);
//
//        while(cursor.moveToNext()){
//            Cerveja cerva = new Cerveja();
//            cerva.setId(cursor.getInt(0));
//            cerva.setNome(cursor.getString(1));
//            cerva.setTipoId(cursor.getInt(2));
//            cerva.setPreco(cursor.getFloat(3));
//            cerveja.add(cerva);
//        }
//
//        return cerveja;
//    }

    public void Excluir(int cervejaId){
        banco.delete("Cerveja", "id = ?", new String[]{ String.valueOf(cervejaId) });
    }

    public void Atualizar(Cerveja cerveja){

        ContentValues values = new ContentValues();
        values.put("Nome", cerveja.getNome());
        values.put("TipoId", cerveja.getTipoId());
        values.put("Preco", cerveja.getPreco());

        banco.update("Cerveja", values, "id = ?", new String[]{ String.valueOf(cerveja.getId()) });
    }

    public List<CervejaLista> obterListaCerveja(){
        List<CervejaLista> cervejas = new ArrayList<>();

        String query = "SELECT C.Id, C.Nome, TC.NOME AS NomeTipo, TC.Litragem, C.Preco FROM CERVEJA AS C INNER JOIN TIPOCERVEJA AS TC " +
                "ON C.TipoId = TC.Id WHERE 1=1";

        Cursor cursor = banco.rawQuery(query,null);

        while(cursor.moveToNext()){
            CervejaLista cerva = new CervejaLista();
            cerva.setId(cursor.getInt(0));
            cerva.setNome(cursor.getString(1));
            cerva.setNomeTipo(cursor.getString(2));
            cerva.setLitragem(cursor.getInt(3));
            cerva.setPreco(cursor.getFloat(4));
            cervejas.add(cerva);
        }

        return cervejas;

    }

    public Cerveja obterCervejaPorId(int cervejaId){
        Cerveja cerveja = new Cerveja();
        Cursor cursor =  banco.rawQuery("SELECT Id, Nome, TipoId, Preco FROM Cerveja WHERE ID = '"+ String.valueOf(cervejaId)+"'", null);

        while(cursor.moveToNext()){
            cerveja.setId(cursor.getInt(0));
            cerveja.setNome(cursor.getString(1));
            cerveja.setTipoId(cursor.getInt(2));
            cerveja.setPreco(cursor.getFloat(3));
        }
        return cerveja;
    }
}
