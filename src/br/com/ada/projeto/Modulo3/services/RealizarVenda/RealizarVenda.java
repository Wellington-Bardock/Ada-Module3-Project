package br.com.ada.projeto.Modulo3.services.RealizarVenda;

import br.com.ada.projeto.Modulo3.entity.*;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class RealizarVenda {

    public static final String DADOS_CARRINHO = """
            %s Produto: %s %s
            %s Quantidade: %s %s
            %s Valor Unit: %s R$ %.2f
            %s Valor total Item: %s R$ %.2f
                                        
            """;
    public static final String TOTAL_CARRINHO = "\n%s Total da Compra: %s R$ %.2f ";
    public static final String DADOS_CLIENTE = """
                                    
            %s Nome do Cliente: %s%s
            %s Data da Compra: %s%s
            """;
    public static final String DADOS_CLIENTE_PT2 = """
            %s CPF: %s%s
            %s Carrinho de Compras: %s
            """;
    public static final String DADOS_CLIENTE_PF = DADOS_CLIENTE_PT2;
    public static final String DADOS_CLIENTE_PJ = """
            %s CNPJ: %s%s
            %s Carrinho de Compras: %s
            """;
    public static final String ESTOQUE_INSUFICIENTE = ConsoleColors.RED_BOLD_BRIGHT + "Estoque Insuficiente!" + ConsoleColors.RESET;
    public static final String PRODUTO_NAO_ENCONTRADO = ConsoleColors.RED_BOLD_BRIGHT + "Produto não encontrado!" + ConsoleColors.RESET;
    Map<Integer, Item> carrinhoCompra;

    Vendas vendas;

    public void abrirVenda(Pessoa cliente) {

        carrinhoCompra = new HashMap<>();

        vendas = new Vendas();

        vendas.setCliente(cliente);

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataVenda = dataAtual.format(formatter);

        vendas.setDataCompra(dataVenda);

    }

    public void setCarrinhoCompra(Produto produto, Integer qtd) {

        Item item = new Item();

        item.setProduto(produto);
        item.setQtdCompra(qtd);

        Item itemCarrinho = carrinhoCompra.get(produto.getId());


        if (itemCarrinho == null) {

            carrinhoCompra.put(produto.getId(), item);
            validacaoQtd(item, produto);

        } else {

            itemCarrinho.setQtdCompra(itemCarrinho.getQtdCompra() + qtd);
            validacaoQtd(itemCarrinho, produto);
        }
    }

    public void finalizarVenda() {

        vendas.setCarrinhoCompra(carrinhoCompra.values().stream().toList());

        System.out.printf(DADOS_CLIENTE,

                ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, vendas.getCliente().getNome(),
                ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, vendas.getDataCompra());

        if (vendas.getCliente() instanceof PessoaFisica) {

            System.out.printf(DADOS_CLIENTE_PF,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET,
                    ((PessoaFisica) vendas.getCliente()).getCpf(),
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET);

        } else if (vendas.getCliente() instanceof PessoaJuridica) {

            System.out.printf(DADOS_CLIENTE_PJ,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET,
                    ((PessoaJuridica) vendas.getCliente()).getCnpj(),
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET);
        }

        BigDecimal totalCompra = BigDecimal.ZERO;

        for (int i = 0; i < vendas.getCarrinhoCompra().size(); i++) {

            Item item = vendas.getCarrinhoCompra().get(i);

            String produto = item.getProduto().getNomeProduto();
            Integer qtdCompra = item.getQtdCompra();
            BigDecimal preco = item.getProduto().getPrecoVenda();
            BigDecimal totalItem = item.getProduto().getPrecoVenda().multiply(BigDecimal.valueOf(qtdCompra));

            totalCompra = totalCompra.add(totalItem);


            System.out.printf(DADOS_CARRINHO,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, produto,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, qtdCompra,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, preco,
                    ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, totalItem);
        }

        System.out.printf(TOTAL_CARRINHO, ConsoleColors.BLUE_BOLD_BRIGHT, ConsoleColors.RESET, totalCompra);

    }

    private void validacaoQtd(Item item, Produto produto) {

        if (produto.getQtdEstoque().compareTo(item.getQtdCompra()) < 0) {

            throw new RuntimeException(ESTOQUE_INSUFICIENTE);

        }
    }

    public Integer getId(String produtoNome) {

        Integer id = null;

        for (Integer key : carrinhoCompra.keySet()) {

            Item item = carrinhoCompra.get(key);

            if (produtoNome.equalsIgnoreCase(item.getProduto().getNomeProduto())) {

                id = key;

            } else {

                throw new RuntimeException(PRODUTO_NAO_ENCONTRADO);

            }
        }
        return id;
    }
}
