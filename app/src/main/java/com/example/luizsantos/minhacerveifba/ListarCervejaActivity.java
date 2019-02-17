package com.example.luizsantos.minhacerveifba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.luizsantos.minhacerveifba.DAO.CervejaDAO;
import com.example.luizsantos.minhacerveifba.Modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ListarCervejaActivity extends AppCompatActivity {

    private ListView listView;
    private CervejaDAO dao;
    private List<CervejaLista> cervejas;
    private List<CervejaLista> cervejaFiltradas = new ArrayList<>();
    private List<TipoCerveja> tipos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cerveja);

        listView = findViewById(R.id.listar_cerveja);
        dao = new CervejaDAO(this);
        cervejas = dao.obterListaCerveja();
        cervejaFiltradas.addAll(cervejas);

        CervejaItemExibir itemExibir = new CervejaItemExibir(this, cervejaFiltradas);
        listView.setAdapter(itemExibir);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textoDigitado) {
                procuraCerveja(textoDigitado);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context, menu);
    }

    public void procuraCerveja(String nome){
        cervejaFiltradas.clear();
        for(CervejaLista c: cervejas){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())){
                cervejaFiltradas.add(c);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroCervejaActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){

        //Pega Aluno que foi selecionado
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final CervejaLista cervejaIndex = cervejaFiltradas.get(menuInfo.position);
        final Cerveja cerveja = dao.obterCervejaPorId(cervejaIndex.getId());

        Intent it = new Intent(this, CadastroCervejaActivity.class);
                it.putExtra("Cerveja", cerveja);
        startActivity(it);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final CervejaLista cervejaExcluir = cervejaFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Realmente deseja Excluir a cerveja?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        cervejaFiltradas.remove(cervejaExcluir);
                        cervejas.remove(cervejaExcluir);
                        dao.Excluir(cervejaExcluir.getId());
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        //cervejas = dao.obterListaCerveja();
        //cervejaFiltradas.addAll(cervejas);
        listView.invalidateViews();
    }
}
