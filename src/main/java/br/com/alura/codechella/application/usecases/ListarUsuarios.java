package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.util.List;
/**
 * interator de caso de uso
 */
public class ListarUsuarios {
    private final RepositorioUsuario repositorioUsuario;

    public ListarUsuarios(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }


    public List<Usuario> listarTodos() {
        return repositorioUsuario.listarTodos();
    }
}
