package br.com.ada.projeto.Modulo3.controller;

import br.com.ada.projeto.Modulo3.menu.Menu;
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
    public static final String TEXTO_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Opção Inválida, tente novamente!" + ConsoleColors.RESET;
    public static final String PRODUTO_NAO_ENCONTRADO = ConsoleColors.RED_BOLD_BRIGHT + "Produto Não Encontrado!" + ConsoleColors.RESET;
    public static final String MENU_INICIAL = Menu.MENU_INICIAL;
    public static final String ID_PRODUTO = Menu.ID_PRODUTO;
    public static final String NOME_DO_PRODUTO = Menu.NOME_DO_PRODUTO;
    public static final String QUANTIDADE_EM_ESTOQUE = Menu.QUANTIDADE_EM_ESTOQUE;
    public static final String VALOR_DO_PRODUTO = Menu.VALOR_DO_PRODUTO;
    public static final String NOVO_PRODUTO = Menu.NOVO_PRODUTO;
    public static final String NOME_DO_CLIENTE = Menu.NOME_DO_CLIENTE;
    public static final String TIPO_CLIENTE = Menu.TIPO_CLIENTE;
    public static final String DOCUMENTO_CLIENTE = Menu.DOCUMENTO_CLIENTE;
    public static final String QTD_PRODUTO_VENDA = Menu.QTD_PRODUTO_VENDA;
    public static final String PRODUTO_SELECIONADO = Menu.PRODUTO_SELECIONADO;
    Scanner scanner = new Scanner(System.in);

    EditarEstoque editarEstoque = new EditarEstoque();

    public void selecaoMenu() {

        try {

            System.out.println(MENU_INICIAL);

            Integer opcaoselecionada = Integer.parseInt(scanner.nextLine());
            Enum<TipoOperacoes> tipoOperacoesEnum = ValidarIntegerMenu.selecaoMenu(opcaoselecionada);

            if (tipoOperacoesEnum.equals(TipoOperacoes.CADASTRO_PRODUTO)) {

                inserirProdutoEstoque();

            } else if (tipoOperacoesEnum.equals(TipoOperacoes.EDITAR_ESTOQUE)) {

                atualizarProdutoEstoque();

            } else if (tipoOperacoesEnum.equals(TipoOperacoes.DELETAR_PRODUTO)) {

                deletarProdutoEstoque();

            } else if (tipoOperacoesEnum.equals(TipoOperacoes.REALIZAR_VENDA)) {

                cadastroCliente();

            } else {

                System.out.println(VALOR_INVALIDO);

            }

        } catch (NumberFormatException numberFormatException) {

            System.out.println(VALOR_INVALIDO);
            selecaoMenu();
        }
    }

    private void inserirProdutoEstoque() {

        String resposta;

        try {

            do {

                System.out.println(Aplicacao.NOME_DO_PRODUTO);
                String produtoNome = scanner.nextLine();
                validacaoTextoVazio(produtoNome);

                System.out.println(Aplicacao.QUANTIDADE_EM_ESTOQUE);
                Integer qtdEstoque = Integer.parseInt(scanner.nextLine());

                System.out.println(Aplicacao.VALOR_DO_PRODUTO);
                BigDecimal valorProduto = new BigDecimal(scanner.nextLine());

                Produto produto = editarEstoque.cadastrarProduto(produtoNome, qtdEstoque, valorProduto);

                editarEstoque.createProdutoLista(produto);

                System.out.println(Aplicacao.NOVO_PRODUTO);
                resposta = scanner.nextLine();

            } while (setBoolean(resposta));

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);

        }
        selecaoMenu();

    }

    private void atualizarProdutoEstoque() {

        System.out.println(Aplicacao.ID_PRODUTO);
        Integer produtoID = Integer.parseInt(scanner.nextLine());

        System.out.println(Aplicacao.QUANTIDADE_EM_ESTOQUE);
        Integer qtdProduto = Integer.parseInt(scanner.nextLine());

        editarEstoque.uploadProdutoLista(produtoID, qtdProduto);

        selecaoMenu();

    }

    private void deletarProdutoEstoque() {

        System.out.println(Aplicacao.ID_PRODUTO);

        Integer produtoID = Integer.parseInt(scanner.nextLine());

        Produto produto = editarEstoque.readProdutoLista(produtoID);
        editarEstoque.deleteProdutoLista(produto);

        selecaoMenu();

    }

    private void cadastroCliente() {

        try {

            System.out.println(Aplicacao.NOME_DO_CLIENTE);
            String nomeCliente = scanner.nextLine();
            validacaoTextoVazio(nomeCliente);

            System.out.println(Aplicacao.TIPO_CLIENTE);
            Integer tpCliente = Integer.parseInt(scanner.nextLine());

            System.out.println(Aplicacao.DOCUMENTO_CLIENTE);
            String documentoCliente = scanner.nextLine();
            validacaoTextoVazio(documentoCliente);

            Pessoa cliente;

            CadastroPessoa cadastroPessoa = CadastroPessoaFactory.getCadastroPessoa(tpCliente);

            cliente = cadastroPessoa.cadastrarPessoa(nomeCliente, documentoCliente);

            criarCarrinhoVenda(cliente);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);

        }
    }

    private void criarCarrinhoVenda(Pessoa cliente) {

        RealizarVenda realizarVenda = new RealizarVenda();

        realizarVenda.abrirVenda(cliente);
        String resposta;

        try {

            do {

                System.out.println(ID_PRODUTO);
                Integer id = Integer.parseInt(scanner.nextLine());

                String nomeProdutoSelecionado = editarEstoque.listaEstoque.get(id).getNomeProduto().toUpperCase();

                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + PRODUTO_SELECIONADO +
                        ConsoleColors.RESET + nomeProdutoSelecionado);

                System.out.println(QTD_PRODUTO_VENDA);
                Integer qtdVenda = Integer.parseInt(scanner.nextLine());

                Produto produto = editarEstoque.readProdutoLista(id);
                realizarVenda.setCarrinhoCompra(produto, qtdVenda);

                System.out.println(NOVO_PRODUTO);
                resposta = scanner.nextLine();

            } while (setBoolean(resposta));

            realizarVenda.finalizarVenda();
            selecaoMenu();

        } catch (NumberFormatException numberFormatException) {
            System.out.println(VALOR_INVALIDO);
            criarCarrinhoVenda(cliente);
        } catch (NullPointerException nullPointerException) {
            System.out.println(PRODUTO_NAO_ENCONTRADO);
            criarCarrinhoVenda(cliente);

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);
            criarCarrinhoVenda(cliente);

        }

    }
}
