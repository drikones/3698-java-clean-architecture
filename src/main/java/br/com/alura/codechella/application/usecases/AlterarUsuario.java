package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

public class AlterarUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public AlterarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario alterarUsuario(String cpf, Usuario usuario) {
        return repositorioUsuario.alteraUsuario(cpf, usuario);
    }
}
