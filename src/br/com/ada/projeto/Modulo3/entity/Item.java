package br.com.ada.projeto.Modulo3.entity;

public class Item {

    private Produto produto;
    private Integer qtdCompra;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdCompra() {
        return qtdCompra;
    }

    public void setQtdCompra(Integer qtdCompra) {
        this.qtdCompra = qtdCompra;
    }
}
