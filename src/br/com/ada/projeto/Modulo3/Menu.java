package br.com.ada.projeto.Modulo3;

import br.com.ada.projeto.Modulo3.controller.Aplicacao;
import br.com.ada.projeto.Modulo3.enums.TipoOperacoes;

public class Menu {
    public static final String ID_PRODUTO = "Por favor, qual o ID desse produto:";
    public static final String NOME_DO_PRODUTO = "Por favor, informar o nome do produto:";
    public static final String QUANTIDADE_EM_ESTOQUE = "Por favor, informar a quantidade em estoque:";
    public static final String VALOR_DO_PRODUTO = "Por favor, informar qual será o valor do produto:";
    public static final String NOVO_PRODUTO = "Gostaria de adicionar um novo produto? (S/N)";
    public static final String NOME_DO_CLIENTE = "Por favor, informar o nome do cliente:";
    public static final String TIPO_CLIENTE = """
            Por favor, informar se o tipo de cliente:
            1 - Pessoa Física
            2 - Pessoa Juridica""";
    public static final String DOCUMENTO_CLIENTE = "Por favor, informar o CPF/CNPJ do cliente:";
    public static final String ID_PRODUTO_VENDA = "Por favor, informar o ID do produto:";
    public static final String QTD_PRODUTO_VENDA = "Por favor, informar a quantidade de produtos vendida:";
    public static final String PRODUTO_SELECIONADO = "Produto Selecionado: ";
    public static final String MENU_INICIAL = """
            Olá Usuário, o que gostaria de fazer?
            1 - Cadastrar Produto
            2 - Editar Quantidade
            3 - Deletar Produto
            4 - Realizar venda
            """;
    public static final String OPCAO_INVALIDA = "Opção Inválida!";

    public static void main(String[] args) {

        Aplicacao aplicacao = new Aplicacao();

        Enum<TipoOperacoes> menu = aplicacao.selecaoMenu(MENU_INICIAL);

        if (menu.equals(TipoOperacoes.CADASTRO_PRODUTO)) {

            aplicacao.inserirProdutoEstoque(NOME_DO_PRODUTO, QUANTIDADE_EM_ESTOQUE, VALOR_DO_PRODUTO, NOVO_PRODUTO);

        } else if (menu.equals(TipoOperacoes.REALIZAR_VENDA)) {

            aplicacao.cadastroCliente(TIPO_CLIENTE, NOME_DO_CLIENTE, DOCUMENTO_CLIENTE);

            aplicacao.criarCarrinhoVenda(QTD_PRODUTO_VENDA, ID_PRODUTO_VENDA, NOVO_PRODUTO, PRODUTO_SELECIONADO);

        } else if (menu.equals(TipoOperacoes.EDITAR_ESTOQUE)) {

            aplicacao.atualizarProdutoEstoque(ID_PRODUTO, QUANTIDADE_EM_ESTOQUE);

        } else if (menu.equals(TipoOperacoes.DELETAR_PRODUTO)) {

            aplicacao.deletarProdutoEstoque(ID_PRODUTO);

        } else {

            System.out.println(OPCAO_INVALIDA);
            main(args);

        }
    }
}