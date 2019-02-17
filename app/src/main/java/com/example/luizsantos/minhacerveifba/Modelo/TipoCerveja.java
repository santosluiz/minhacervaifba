package com.example.luizsantos.minhacerveifba.Modelo;

public class TipoCerveja {

    private int Id;

    private String Nome;

    private int Litragem;

    public int getLitragem() {
        return Litragem;
    }

    public void setLitragem(int litragem) {
        Litragem = litragem;
    }

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
}
