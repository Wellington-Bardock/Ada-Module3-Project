package br.com.ada.projeto.Modulo3.enums;

public enum TipoOperacoes {

    CADASTRO_PRODUTO(1),
    REALIZAR_VENDA(2);

    private final Integer tpOperacao;

    TipoOperacoes(Integer tpOperacao) {
        this.tpOperacao = tpOperacao;

    }

    public Integer getTpCliente() {
        return tpOperacao;
    }

}


