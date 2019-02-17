package com.example.luizsantos.minhacerveifba;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizsantos.minhacerveifba.Domain.Cerveja;

import java.util.List;

public class CervejaItemExibir extends BaseAdapter {

    private List<Cerveja> cerveja;
    private Activity activity;

    public CervejaItemExibir(Activity activity, List<Cerveja> cerveja) {
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
        View view = activity.getLayoutInflater().inflate(R.layout.item_exibir, parent, false);
        TextView nome = view.findViewById(R.id.txt_nome);
        TextView tipo = view.findViewById(R.id.txt_tipo);
        TextView litragem = view.findViewById(R.id.txt_litragem);
        TextView preco = view.findViewById(R.id.txt_preco);

        Cerveja cerva = cerveja.get(position);

        nome.setText(cerva.getNome());
        tipo.setText(cerva.getTipoId());
        litragem.setText(cerva.getLitragem());
        preco.setText(String.valueOf(cerva.getPreco()));

        return view;
    }
}
