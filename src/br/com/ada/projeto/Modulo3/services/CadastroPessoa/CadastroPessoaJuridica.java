package br.com.ada.projeto.Modulo3.services.CadastroPessoa;

import br.com.ada.projeto.Modulo3.entity.Pessoa;
import br.com.ada.projeto.Modulo3.entity.PessoaJuridica;

public class CadastroPessoaJuridica implements CadastroPessoa{


    @Override
    public Pessoa cadastrarPessoa(String nome, String documento) {

        PessoaJuridica pessoa = new PessoaJuridica();
        pessoa.setNome(nome);
        pessoa.setCnpj(documento);

        return pessoa;
    }
}
