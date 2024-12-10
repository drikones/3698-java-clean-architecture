package br.com.alura.codechella.config;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.application.usecases.AlterarUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.ExcluirUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.infrastructure.gateways.RepositorioUsuarioJPA;
import br.com.alura.codechella.infrastructure.gateways.UsuarioEntityMapper;
import br.com.alura.codechella.infrastructure.persistence.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {


    @Bean
    public UsuarioEntityMapper usuarioEntityMapper() {
        return new UsuarioEntityMapper();
    }


    @Bean
    public RepositorioUsuario repositorioUsuario(UsuarioRepository repository, UsuarioEntityMapper mapper) {
        return new RepositorioUsuarioJPA(repository, mapper);
    }

    @Bean
    public CriarUsuario criarUsuario(RepositorioUsuario repositorioUsuario) {
        return new CriarUsuario(repositorioUsuario);
    }

    @Bean
    public ListarUsuarios listarTodosUsuarios(RepositorioUsuario repositorioUsuario) {
        return new ListarUsuarios(repositorioUsuario);
    }

    @Bean
    public AlterarUsuario alterarUsuario (RepositorioUsuario repositorioUsuario) {
        return new AlterarUsuario(repositorioUsuario);
    }

    @Bean
    public ExcluirUsuario excluirUsuario (RepositorioUsuario repositorioUsuario) {
        return new ExcluirUsuario(repositorioUsuario);
    }
}
