package br.com.alura.codechella.domain.entities.usuario;

import br.com.alura.codechella.domain.Endereco;

import java.time.LocalDate;

/**
 *
 * Aqui estamos utilizando o padrão Builder para criar um objeto de usuário.
 */
public class FabricaUsuario {

    private Usuario usuario;

    public Usuario comNomeCPFNascimento(String nome, String cpf, LocalDate nascimento) {
        this.usuario =  new Usuario(cpf, nome, nascimento, "");
        return this.usuario;
    }

    public Usuario incluiEndereco(String cep, Integer numero, String complemento) {
        this.usuario.setEndereco(new Endereco(cep, numero, complemento));
        return this.usuario;
    }
}
