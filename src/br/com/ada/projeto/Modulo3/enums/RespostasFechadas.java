package br.com.ada.projeto.Modulo3.enums;

public enum RespostasFechadas {

    SIM("S"), NAO("N");
    private final String tpResposta;

    RespostasFechadas(String tpResposta) {

        this.tpResposta = tpResposta;
    }

    public String getTpResposta() {
        return tpResposta;
    }
}
