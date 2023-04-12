package br.com.ada.projeto.Modulo3.entity;

import java.util.List;

public class Vendas {

    Pessoa cliente;
    String dataCompra;
    List<Item> carrinhoCompra;

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public List<Item> getCarrinhoCompra() {
        return carrinhoCompra;
    }

    public void setCarrinhoCompra(List<Item> carrinhoCompra) {
        this.carrinhoCompra = carrinhoCompra;
    }
}
