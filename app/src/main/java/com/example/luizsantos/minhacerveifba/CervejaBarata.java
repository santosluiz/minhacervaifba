package com.example.luizsantos.minhacerveifba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.luizsantos.minhacerveifba.Modelo.CervejaLista;

import java.util.List;

public class CervejaBarata {
    private List<CervejaLista> cerveja;
    private Activity activity;

    /*public CervejaItemExibir(Activity activity, List<CervejaLista> cerveja) {
        this.activity = activity;
        this.cerveja = cerveja;
    }

    @Override
    public int getCount() {
        return cerveja.size();
    }

    @Override
    public Object getItem(int position) {
        return cerveja.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cerveja.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Cria um item novo na tela de exibir (a cada chamada)
        View view = activity.getLayoutInflater().inflate(R.layout.activity_cerveja_barata, parent, false);
        TextView nome = view.findViewById(R.id.txt_nomeBarata);
        TextView tipo = view.findViewById(R.id.txt_tipoBarata);
        TextView preco = view.findViewById(R.id.txt_precoBarata);

        CervejaLista cerva = cerveja.get(position);

        nome.setText(cerva.getNome());
        tipo.setText("".concat(cerva.getNomeTipo() + " " + cerva.getLitragem() + " ML"));
        preco.setText(String.valueOf(cerva.getPreco()));

        return view;
    } */


}
