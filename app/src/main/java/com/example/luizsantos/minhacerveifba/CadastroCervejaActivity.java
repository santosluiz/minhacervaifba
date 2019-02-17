package com.example.luizsantos.minhacerveifba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroCervejaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText tipo;
    private EditText litragem;
    private EditText preco;
    private CervejaDAO dao;
    private Cerveja cerveja = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cerveja);

        nome = findViewById(R.id.editNome);
        tipo = findViewById(R.id.editTipo);
        litragem = findViewById(R.id.editLitragem);
        preco = findViewById(R.id.editPreco);
        dao = new CervejaDAO(this);

        //Adiciona a instância da Cerveja para fazer o update
        Intent it = getIntent();
        if(it.hasExtra("cerveja")){
            cerveja = (Cerveja) it.getSerializableExtra("cerveja");
            nome.setText(cerveja.getNome());
            tipo.setText(cerveja.getTipo());
            litragem.setText(cerveja.getLitragem());
            preco.setText(cerveja.getPreco());

        }
    }

    public void salvar(View view){

        //Se a cerveja for null, é porque não tem registro, então salva.
        if(cerveja == null) {
            cerveja = new Cerveja();
            cerveja.setNome(nome.getText().toString());
            cerveja.setTipo(tipo.getText().toString());
            cerveja.setLitragem(litragem.getText().toString());
            cerveja.setPreco(preco.getText().toString());
            long id = dao.inserir(cerveja);
            Toast.makeText(this, "Cerveja com id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            cerveja.setNome(nome.getText().toString());
            cerveja.setTipo(tipo.getText().toString());
            cerveja.setLitragem(litragem.getText().toString());
            cerveja.setPreco(preco.getText().toString());
            dao.atualizar(cerveja);
            Toast.makeText(this, "Cerveja atualizada!", Toast.LENGTH_SHORT).show();
        }



    }
}
