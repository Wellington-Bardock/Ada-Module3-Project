package br.com.ada.projeto.Modulo3.controller;

import br.com.ada.projeto.Modulo3.entity.Pessoa;
import br.com.ada.projeto.Modulo3.entity.Produto;
import br.com.ada.projeto.Modulo3.enums.RespostasFechadas;
import br.com.ada.projeto.Modulo3.enums.TipoOperacoes;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;
import br.com.ada.projeto.Modulo3.factory.CadastroPessoaFactory;
import br.com.ada.projeto.Modulo3.services.CadastroPessoa.CadastroPessoa;
import br.com.ada.projeto.Modulo3.services.CadastroProduto.CadastroProduto;
import br.com.ada.projeto.Modulo3.services.RealizarVenda.RealizarVenda;

import java.math.BigDecimal;
import java.util.Scanner;

public class Aplicacao {

    public static final String VALOR_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Valor Inválido!" + ConsoleColors.RESET;
    public static final String TEXTO_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Nome do Produto Vázio, tente novamente!" + ConsoleColors.RESET;
    public static final String PRODUTO_NAO_ENCONTRADO = ConsoleColors.RED_BOLD_BRIGHT + "Produto Não Encontrado!" + ConsoleColors.RESET;
    Scanner scanner = new Scanner(System.in);

    CadastroProduto cadastroProduto = new CadastroProduto();

    public Enum<TipoOperacoes> selecaoMenu(String msgMenu) {

        System.out.println(msgMenu);

        int opcaoselecionada = Integer.parseInt(scanner.nextLine());

        if (opcaoselecionada==TipoOperacoes.CADASTRO_PRODUTO.getTpCliente()) {

            return TipoOperacoes.CADASTRO_PRODUTO;

        } else if (opcaoselecionada==TipoOperacoes.REALIZAR_VENDA.getTpCliente()) {

            return TipoOperacoes.REALIZAR_VENDA;

        } else {

            throw new RuntimeException(VALOR_INVALIDO);
        }
    }

    public void inserirProdutoEstoque(String msgNomeProduto, String msgQtdEstoque, String msgValor, String msgId,
                                      String msgLoop) {
        do try {

            System.out.println(msgNomeProduto);
            String produtoNome = scanner.nextLine();
            validacaoTextoVazio(produtoNome);

            System.out.println(msgId);
            Integer idProduto = Integer.parseInt(scanner.nextLine());

            System.out.println(msgQtdEstoque);
            Integer qtdEstoque = Integer.parseInt(scanner.nextLine());

            System.out.println(msgValor);
            BigDecimal valorProduto = new BigDecimal(scanner.nextLine());

            Produto produto = cadastroProduto.cadastrarProduto(produtoNome, qtdEstoque, valorProduto, idProduto);

            cadastroProduto.createProdutoLista(produto);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);

        } while (setBoolean(msgLoop));

    }

    private Pessoa cliente;

    public void cadastroCliente(String msgTpCliente, String msgNomeCliente, String msgDocumentoCliente) {

        try {

            System.out.println(msgNomeCliente);
            String nomeCliente = scanner.nextLine();
            validacaoTextoVazio(nomeCliente);

            System.out.println(msgTpCliente);
            Integer tpCliente = Integer.parseInt(scanner.nextLine());

            System.out.println(msgDocumentoCliente);
            String documentoCliente = scanner.nextLine();
            validacaoTextoVazio(documentoCliente);

            CadastroPessoa cadastroPessoa = CadastroPessoaFactory.getCadastroPessoa(tpCliente);

            cliente = cadastroPessoa.cadastrarPessoa(nomeCliente, documentoCliente);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);
        }
    }

    RealizarVenda realizarVenda = new RealizarVenda();

    public void criarCarrinhoVenda(String msgQtdVenda, String msgId, String msgLoop, String msgSelecaoProd) {

        realizarVenda.abrirVenda(cliente);

        do try{

            System.out.println(msgId);
            Integer id = Integer.parseInt(scanner.nextLine());

            String nomeProdutoSelecionado = cadastroProduto.listaEstoque.get(id).getNomeProduto().toUpperCase();

            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + msgSelecaoProd + ConsoleColors.RESET +
                    id + nomeProdutoSelecionado);

            System.out.println(msgQtdVenda);
            Integer qtdVenda = Integer.parseInt(scanner.nextLine());


            Produto produto = cadastroProduto.readProdutoLista(id);
            realizarVenda.setCarrinhoCompra(produto, qtdVenda);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);
        } catch (NullPointerException nullPointerException) {
            System.out.println(PRODUTO_NAO_ENCONTRADO);

        }

        while (setBoolean(msgLoop));

        realizarVenda.finalizarVenda();
    }

    public boolean setBoolean(String msg) {

        try {

            System.out.println(msg);
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase(RespostasFechadas.SIM.getTpResposta())) {

                return true;

            } else if (resposta.equalsIgnoreCase(RespostasFechadas.NAO.getTpResposta())) {

                return false;

            } else {

                throw new RuntimeException();

            }

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);
            return setBoolean(msg);
        }
    }

    private void validacaoTextoVazio(String texto) {

        if (texto.isEmpty()) {
            throw new RuntimeException();
        }
    }
}
