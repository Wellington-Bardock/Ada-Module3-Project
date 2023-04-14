package br.com.ada.projeto.Modulo3.services.validador;

import br.com.ada.projeto.Modulo3.enums.RespostasFechadas;
import br.com.ada.projeto.Modulo3.extras.ConsoleColors;

public class ValidarBoolean {

    public static final String TEXTO_INVALIDO = ConsoleColors.RED_BOLD_BRIGHT + "Opção Inválida, tente novamente!" + ConsoleColors.RESET;

    public static boolean setBoolean(String resposta) {

        try {

            if (resposta.equalsIgnoreCase(RespostasFechadas.SIM.getTpResposta())) {

                return true;

            } else if (resposta.equalsIgnoreCase(RespostasFechadas.NAO.getTpResposta())) {

                return false;

            } else {

                throw new RuntimeException();

            }

        } catch (RuntimeException runtimeException) {
            System.out.println(TEXTO_INVALIDO);
            return setBoolean(resposta);
        }
    }
}
