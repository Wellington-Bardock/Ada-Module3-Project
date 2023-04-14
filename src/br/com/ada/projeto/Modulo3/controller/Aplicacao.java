package br.com.ada.projeto.Modulo3.controller;

import br.com.ada.projeto.Modulo3.entity.Pessoa;
import br.com.ada.projeto.Modulo3.entity.Produto;
import br.com.ada.projeto.Modulo3.enums.TipoOperacoes;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;
import br.com.ada.projeto.Modulo3.factory.CadastroPessoaFactory;
import br.com.ada.projeto.Modulo3.services.CadastroPessoa.CadastroPessoa;
import br.com.ada.projeto.Modulo3.services.editacaoEstoque.EditarEstoque;
import br.com.ada.projeto.Modulo3.services.realizarVenda.RealizarVenda;
import br.com.ada.projeto.Modulo3.services.validador.ValidarIntegerMenu;

import java.math.BigDecimal;
import java.util.Scanner;

import static br.com.ada.projeto.Modulo3.services.validador.ValidarBoolean.setBoolean;
import static br.com.ada.projeto.Modulo3.services.validador.ValidarString.validacaoTextoVazio;

public class Aplicacao {
    public static final String VALOR_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Valor Inválido!" + ConsoleColors.RESET;
    public static final String TEXTO_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Nome do Produto Vázio, tente novamente!" + ConsoleColors.RESET;
    public static final String PRODUTO_NAO_ENCONTRADO = ConsoleColors.RED_BOLD_BRIGHT + "Produto Não Encontrado!" + ConsoleColors.RESET;
    Scanner scanner = new Scanner(System.in);

    EditarEstoque editarEstoque = new EditarEstoque();

    public Enum<TipoOperacoes> selecaoMenu(String msgMenu) {

        System.out.println(msgMenu);

        Integer opcaoselecionada = Integer.parseInt(scanner.nextLine());

        return ValidarIntegerMenu.selecaoMenu(opcaoselecionada);

    }

    public void inserirProdutoEstoque(String msgNomeProduto, String msgQtdEstoque, String msgValor, String msgLoop) {

        String resposta=null;

        do try {

            System.out.println(msgNomeProduto);
            String produtoNome = scanner.nextLine();
            validacaoTextoVazio(produtoNome);

            System.out.println(msgQtdEstoque);
            Integer qtdEstoque = Integer.parseInt(scanner.nextLine());

            System.out.println(msgValor);
            BigDecimal valorProduto = new BigDecimal(scanner.nextLine());

            Produto produto = editarEstoque.cadastrarProduto(produtoNome, qtdEstoque, valorProduto);

            editarEstoque.createProdutoLista(produto);

            System.out.println(msgLoop);
            resposta = scanner.nextLine();


        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);

        } while (setBoolean(resposta));

    }

    public void deletarProdutoEstoque (String msgId) {

        System.out.println(msgId);

        Integer produtoID = Integer.parseInt(scanner.nextLine());

        Produto produto = editarEstoque.readProdutoLista(produtoID);
        editarEstoque.deleteProdutoLista(produto);

    }

    public void atualizarProdutoEstoque (String msgId, String msgQtdEstoque) {

        System.out.println(msgId);
        Integer produtoID = Integer.parseInt(scanner.nextLine());

        System.out.println(msgQtdEstoque);
        Integer qtdProduto = Integer.parseInt(scanner.nextLine());

        editarEstoque.uploadProdutoLista(produtoID, qtdProduto);

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
        String resposta = null;

        do try{

            System.out.println(msgId);
            Integer id = Integer.parseInt(scanner.nextLine());

            String nomeProdutoSelecionado = editarEstoque.listaEstoque.get(id).getNomeProduto().toUpperCase();

            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + msgSelecaoProd + ConsoleColors.RESET +
                    id + nomeProdutoSelecionado);

            System.out.println(msgQtdVenda);
            Integer qtdVenda = Integer.parseInt(scanner.nextLine());


            Produto produto = editarEstoque.readProdutoLista(id);
            realizarVenda.setCarrinhoCompra(produto, qtdVenda);

            System.out.println(msgLoop);
            resposta = scanner.nextLine();

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);
        } catch (NullPointerException nullPointerException) {
            System.out.println(PRODUTO_NAO_ENCONTRADO);

        }

        while (setBoolean(resposta));

        realizarVenda.finalizarVenda();
    }
}
