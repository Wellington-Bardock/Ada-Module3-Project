package br.com.ada.projeto.Modulo3.services.validador;

import br.com.ada.projeto.Modulo3.enums.RespostasFechadas;

public class ValidarBoolean {
    public static boolean setBoolean(String resposta) {

        if (resposta.equalsIgnoreCase(RespostasFechadas.SIM.getTpResposta())) {

            return true;

        } else if (resposta.equalsIgnoreCase(RespostasFechadas.NAO.getTpResposta())) {

            return false;

        } else {
            System.out.println("Opção Inválida!");
            return true;

        }
    }
}
