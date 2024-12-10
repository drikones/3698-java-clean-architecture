package br.com.alura.codechella.application.usecases;


import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

/**
 * interator de caso de uso
 */
public class CriarUsuario{

    private final RepositorioUsuario repositorioUsuario;

    public CriarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }


    public Usuario cadastrarUsuario(Usuario usuario) {
        return repositorioUsuario.cadastrarUsuario(usuario);
    }
}
