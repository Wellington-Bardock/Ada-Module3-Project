package br.com.ada.projeto.Modulo3.services.validador;

import br.com.ada.projeto.Modulo3.enums.TipoOperacoes;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;

public class ValidarIntegerMenu {

    public static final String VALOR_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Valor Inválido!" + ConsoleColors.RESET;

    public static Enum<TipoOperacoes> selecaoMenu(Integer opcaoselecionada) {

        if (opcaoselecionada.equals(TipoOperacoes.CADASTRO_PRODUTO.getTpCliente())) {

            return TipoOperacoes.CADASTRO_PRODUTO;

        } else if (opcaoselecionada.equals(TipoOperacoes.REALIZAR_VENDA.getTpCliente())) {

            return TipoOperacoes.REALIZAR_VENDA;

        } else if (opcaoselecionada.equals(TipoOperacoes.EDITAR_ESTOQUE.getTpCliente())) {

            return TipoOperacoes.EDITAR_ESTOQUE;

        } else if (opcaoselecionada.equals(TipoOperacoes.DELETAR_PRODUTO.getTpCliente())) {

            return TipoOperacoes.DELETAR_PRODUTO;

        } else {

            throw new RuntimeException(VALOR_INVALIDO);
        }
    }
}
