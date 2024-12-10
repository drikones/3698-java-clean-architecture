package br.com.alura.codechella.application.usecases.impl;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.domain.entities.usuario.FabricaUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infrastructure.gateways.RepositorioUsuarioJPA;
import br.com.alura.codechella.infrastructure.gateways.UsuarioEntityMapper;
import br.com.alura.codechella.infrastructure.persistence.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListarUsuariosTest {

    private RepositorioUsuario repositorioUsuario;
    private UsuarioRepository repositorio;
    private UsuarioEntityMapper mapper;

    @BeforeEach
    public void setUp(){
        repositorio = mock(UsuarioRepository.class);
        mapper = new UsuarioEntityMapper();
        repositorioUsuario = new RepositorioUsuarioJPA(repositorio, mapper);
    }

    @DisplayName("Deve listar todos os usuarios")
    @Test
    public void deveListarUsuarios() {
       try{
           String cpf = "123.456.789-10";
           String nome = "Fulano";
           LocalDate nascimento = LocalDate.of(2000, 1, 1);

           FabricaUsuario fabricaUsuario = new FabricaUsuario();
           CriarUsuario criarUsuario = new CriarUsuario(repositorioUsuario);
           Usuario usuario  = criarUsuario.cadastrarUsuario(fabricaUsuario.comNomeCPFNascimento(nome, cpf, nascimento));

           when(repositorio.findAll()).thenReturn(List.of(usuario).stream().map(u -> mapper.toEntity(u)).toList());

           ListarUsuarios listarUsuarios = new ListarUsuarios(repositorioUsuario);
           List<Usuario> usuarios = listarUsuarios.listarTodos();
           Assertions.assertThat(usuarios).isNotEmpty();
           assertEquals(cpf, usuarios.get(0).getCpf());
           assertEquals(nome, usuarios.get(0).getNome());
           assertEquals(nascimento, usuarios.get(0).getNascimento());


       }catch(IllegalArgumentException e){
           fail("Não deve lançar exceção");
       }
    }


    @DisplayName("Deve lançar uma exceção ao tentar listar todos os usuarios")
    @Test
    public void deveLançarExcecaoAoListar() {
        try{
            String cpf = "123.456.789";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);

            FabricaUsuario fabricaUsuario = new FabricaUsuario();
            CriarUsuario criarUsuario = new CriarUsuario(repositorioUsuario);
            Usuario usuario  = criarUsuario.cadastrarUsuario(fabricaUsuario.comNomeCPFNascimento(nome, cpf, nascimento));

            when(repositorio.findAll()).thenReturn(List.of(usuario).stream().map(u -> mapper.toEntity(u)).toList());

            ListarUsuarios listarUsuarios = new ListarUsuarios(repositorioUsuario);
            List<Usuario> usuarios = listarUsuarios.listarTodos();
            fail("deve lançar exceção");

        }catch(IllegalArgumentException e){
           assertThrows(IllegalArgumentException.class, () -> {
               throw new IllegalArgumentException("CPF no padrão errado!");
           });
        }
    }
}
