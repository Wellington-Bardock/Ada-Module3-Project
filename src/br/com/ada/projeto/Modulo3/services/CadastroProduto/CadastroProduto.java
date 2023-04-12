package br.com.ada.projeto.Modulo3.services.CadastroProduto;

import br.com.ada.projeto.Modulo3.extras.ConsoleColors;
import br.com.ada.projeto.Modulo3.entity.Produto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CadastroProduto {
    public Produto cadastrarProduto(String nomeProduto, Integer qtd, BigDecimal preco, Integer id) {

        Produto produto = new Produto();

        produto.setId(id);
        produto.setNomeProduto(nomeProduto);
        produto.setQtdEstoque(qtd);
        produto.setPrecoVenda(preco);



        return produto;

    }

    public Map<Integer, Produto> listaEstoque = new HashMap<>();


    public void createProdutoLista(Produto produto) {
        listaEstoque.put(produto.getId(), produto);

    }

    public void deleteProdutoLista(Produto produto) {
        listaEstoque.remove(produto.getId(),produto);

    }

    public void uploadProdutoLista(Integer id, Integer qtd) {

        Produto produtoEstoque = listaEstoque.get(id);

        if (produtoEstoque==null) {

            throw new RuntimeException(ConsoleColors.RED_BOLD_BRIGHT + "Produto não cadastrado!" + ConsoleColors.RESET);

        } else {

            Integer qtdRestante = produtoEstoque.getQtdEstoque() - qtd;

            produtoEstoque.setQtdEstoque(qtdRestante);

        }
    }

    public Produto readProdutoLista(Integer id) {

        return listaEstoque.get(id);
    }

}
