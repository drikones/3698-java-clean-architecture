package br.com.alura.codechella.application.gateways;

import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.util.List;

/**
 * Porta de entrada do caso de uso
 */
public interface RepositorioUsuario {

    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario alteraUsuario(String cpf, Usuario usuario);

    void excluiUsuario(String cpf);
}
