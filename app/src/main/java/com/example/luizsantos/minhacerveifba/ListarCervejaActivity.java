package com.example.luizsantos.minhacerveifba;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarCervejaActivity extends AppCompatActivity {

    private ListView listView;
    private CervejaDAO dao;
    private List<Cerveja> cerveja;
    private List<Cerveja> cervejaFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cerveja);

        listView = findViewById(R.id.listar_cerveja);
        dao = new CervejaDAO(this);
        cerveja = dao.obterTodos();
        cervejaFiltradas.addAll(cerveja);
        //ArrayAdapter<Cerveja> adaptador = new ArrayAdapter<Cerveja>(this, android.R.layout.simple_list_item_1, cervejaFiltradas);
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
        for(Cerveja c: cerveja){
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
        final Cerveja cervejaAtualizar = cervejaFiltradas.get(menuInfo.position);

        Intent it = new Intent(this, CadastroCervejaActivity.class);
                it.putExtra("cerveja", cervejaAtualizar);
        startActivity(it);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cerveja cervejaExcluir = cervejaFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Realmente deseja excluir a cerveja?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        cervejaFiltradas.remove(cervejaExcluir);
                        cerveja.remove(cervejaExcluir);
                        dao.excluir(cervejaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        cerveja = dao.obterTodos();
        cervejaFiltradas.addAll(cerveja);
        listView.invalidateViews();
    }
}
