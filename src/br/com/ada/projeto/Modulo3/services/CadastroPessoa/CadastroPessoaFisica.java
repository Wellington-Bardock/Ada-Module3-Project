package br.com.ada.projeto.Modulo3.services.CadastroPessoa;

import br.com.ada.projeto.Modulo3.entity.Pessoa;
import br.com.ada.projeto.Modulo3.entity.PessoaFisica;

public class CadastroPessoaFisica implements CadastroPessoa{


    @Override
    public Pessoa cadastrarPessoa(String nome, String documento) {

        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setNome(nome);
        pessoa.setCpf(documento);

        return pessoa;
    }
}
