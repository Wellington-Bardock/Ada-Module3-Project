package br.com.ada.projeto.Modulo3.enums;

public enum TipoOperacoes {

    CADASTRO_PRODUTO(1),
    REALIZAR_VENDA(4),
    EDITAR_ESTOQUE(2),
    DELETAR_PRODUTO(3);

    private final Integer tpOperacao;

    TipoOperacoes(Integer tpOperacao) {
        this.tpOperacao = tpOperacao;

    }

    public Integer getTpCliente() {
        return tpOperacao;
    }

}


