package com.example.luizsantos.minhacerveifba.Modelo;

import java.io.Serializable;

public class Cerveja implements Serializable {

    private int Id;

    private String Nome;

    private int TipoId;

    private float preco;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getTipoId() {
        return TipoId;
    }

    public void setTipoId(int tipoId) {
        TipoId = tipoId;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return Nome;
    }
}
