package com.example.luizsantos.minhacerveifba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luizsantos.minhacerveifba.DAO.CervejaDAO;
import com.example.luizsantos.minhacerveifba.DAO.TipoCervejaDAO;
import com.example.luizsantos.minhacerveifba.Domain.Cerveja;
import com.example.luizsantos.minhacerveifba.Domain.TipoCerveja;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroCervejaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText preco;
    private CervejaDAO cervejaDAO;
    private TipoCervejaDAO tipoCervejaDAO;
    private Cerveja cerveja = null;
    private Spinner spinnerTipoCerveja;
    private int tipoId;
    private HashMap<String ,String> tiposHashMap = new HashMap<String,String>();
    ArrayList<String> tiposList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cerveja);
        spinnerTipoCerveja = (Spinner)findViewById(R.id.editTipo);
        tipoCervejaDAO = new TipoCervejaDAO(this);

        ArrayList<TipoCerveja> tipos = tipoCervejaDAO.ObterTipos();

        for(TipoCerveja tipo : tipos){
            tiposHashMap.put(tipo.getNome().concat(" " + String.valueOf(tipo.getLitragem()) + "ML"), String.valueOf(tipo.getId()));
        }

        for(TipoCerveja tipo : tipos){
            tiposList.add(tipo.getNome().concat(" " + String.valueOf(tipo.getLitragem()) + "ML"));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tiposList);

        spinnerTipoCerveja.setAdapter(adapter);

        spinnerTipoCerveja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1, int position, long arg3) {
               switch(adapterView.getId()) {
                   case R.id.editTipo:
                       String nomeTipo = adapterView.getItemAtPosition(position).toString();
                       tipoId = Integer.valueOf(tiposHashMap.get(nomeTipo));
                       break;
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        nome = findViewById(R.id.editNome);
        preco = findViewById(R.id.editPreco);
        cervejaDAO = new CervejaDAO(this);

        //Adiciona a instância da Cerveja para fazer o update
        Intent it = getIntent();
        if(it.hasExtra("Cerveja")){
            cerveja = (Cerveja) it.getSerializableExtra("Cerveja");
            nome.setText(cerveja.getNome());
            spinnerTipoCerveja.setSelection(cerveja.getTipoId() - 1);
            preco.setText(String.valueOf(cerveja.getPreco()));
        }
    }

    public void Salvar(View view){
        //Se a cerveja for null, é porque não tem registro, então salva.
        if(cerveja == null) {
            cerveja = new Cerveja();
            cerveja.setNome(nome.getText().toString());
            cerveja.setTipoId(tipoId);
            cerveja.setPreco(Float.valueOf(preco.getText().toString()));
            long id = cervejaDAO.Inserir(cerveja);
            Toast.makeText(this, "Cerveja com id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            cerveja.setNome(nome.getText().toString());
            cerveja.setTipoId(tipoId);
            cerveja.setPreco(Float.valueOf(preco.getText().toString()));
            cervejaDAO.Atualizar(cerveja);
            Toast.makeText(this, "Cerveja atualizada!", Toast.LENGTH_SHORT).show();
        }

        Intent homepage = new Intent(this, ListarCervejaActivity.class);
        startActivity(homepage);
    }

}
