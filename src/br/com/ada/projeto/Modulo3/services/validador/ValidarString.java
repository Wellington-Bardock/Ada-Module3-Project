package br.com.ada.projeto.Modulo3.services.validador;

public class ValidarString {
    public static void validacaoTextoVazio(String texto) {

        if (texto.isEmpty()) {
            throw new RuntimeException();
        }
    }

}
