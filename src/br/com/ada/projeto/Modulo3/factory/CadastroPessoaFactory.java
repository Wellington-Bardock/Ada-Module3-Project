package br.com.ada.projeto.Modulo3.factory;

import br.com.ada.projeto.Modulo3.services.CadastroPessoa.CadastroPessoaFisica;
import br.com.ada.projeto.Modulo3.services.CadastroPessoa.CadastroPessoaJuridica;
import br.com.ada.projeto.Modulo3.enums.TipoCliente;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;
import br.com.ada.projeto.Modulo3.services.CadastroPessoa.CadastroPessoa;

public class CadastroPessoaFactory {

    public static final String OPCAO_INVALIDA = ConsoleColors.RED_BOLD_BRIGHT + "Opção Inválida!" + ConsoleColors.RESET;

    public static CadastroPessoa getCadastroPessoa(Integer tpCliente) {

        CadastroPessoa cadastroPessoa;

        if (tpCliente.equals(TipoCliente.PESSOA_FISICA.getTpCliente())) {

            cadastroPessoa = new CadastroPessoaFisica();

        } else if (tpCliente.equals(TipoCliente.PESSOA_JURIDICA.getTpCliente())) {

            cadastroPessoa = new CadastroPessoaJuridica();

        } else {
            throw new RuntimeException(OPCAO_INVALIDA);

        }
        return cadastroPessoa;
    }
}
