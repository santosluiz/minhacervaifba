package com.example.luizsantos.minhacerveifba.Modelo;

public class CervejaLista {
    private int Id;

    private String Nome;

    private String NomeTipo;

    private float Preco;

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

    public String getNomeTipo() {
        return NomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        NomeTipo = nomeTipo;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float preco) {
        Preco = preco;
    }
}
