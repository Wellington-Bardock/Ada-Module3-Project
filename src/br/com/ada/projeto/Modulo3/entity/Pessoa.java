package br.com.ada.projeto.Modulo3.entity;

public abstract class Pessoa {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    protected String nome;

}
