package com.example.luizsantos.minhacerveifba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button cadastrarExibirBtn;
    private Button cervejaBarataBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_home);
    }

    public void goToListarActivity(View view) {
        Intent intent = new Intent(this, ListarCervejaActivity.class);
        startActivity(intent);
    }
}
