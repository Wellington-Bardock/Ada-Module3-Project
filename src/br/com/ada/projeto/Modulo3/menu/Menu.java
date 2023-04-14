package br.com.ada.projeto.Modulo3.menu;

import br.com.ada.projeto.Modulo3.controller.Aplicacao;
public class Menu {
    public static final String MENU_INICIAL = """
            O que gostaria de fazer?
            1 - Cadastrar Produto
            2 - Editar Quantidade
            3 - Deletar Produto
            4 - Realizar venda
            """;

    public static final String TIPO_CLIENTE = """
            Por favor, informar se o tipo de cliente:
            1 - Pessoa Física
            2 - Pessoa Juridica""";

    public static final String ID_PRODUTO = "Por favor, qual o ID do produto:";
    public static final String NOME_DO_PRODUTO = "Por favor, informar o nome do produto:";
    public static final String QUANTIDADE_EM_ESTOQUE = "Por favor, informar a quantidade em estoque:";
    public static final String VALOR_DO_PRODUTO = "Por favor, informar qual será o valor do produto:";
    public static final String NOVO_PRODUTO = "Gostaria de adicionar um novo produto? (S/N)";
    public static final String NOME_DO_CLIENTE = "Por favor, informar o nome do cliente:";
    public static final String DOCUMENTO_CLIENTE = "Por favor, informar o CPF/CNPJ do cliente:";
    public static final String QTD_PRODUTO_VENDA = "Por favor, informar a quantidade de produtos vendida:";
    public static final String PRODUTO_SELECIONADO = "Produto Selecionado: ";
    public static void main(String[] args) {



        Aplicacao aplicacao = new Aplicacao();

        System.out.println("Olá Usuário, bem vindo!");
        aplicacao.selecaoMenu();

    }
}