package br.com.alura.codechella.application.usecases.impl;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.domain.entities.usuario.FabricaUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infrastructure.gateways.RepositorioUsuarioJPA;
import br.com.alura.codechella.infrastructure.gateways.UsuarioEntityMapper;
import br.com.alura.codechella.infrastructure.persistence.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Fail.fail;
import static org.mockito.Mockito.mock;

public class CriarUsuarioTest {

    private RepositorioUsuario repositorioUsuario;
    private UsuarioRepository repositorio;
    private UsuarioEntityMapper mapper;

    @BeforeEach
    public void setUp(){
        repositorio = mock(UsuarioRepository.class);
        mapper = new UsuarioEntityMapper();
        repositorioUsuario = new RepositorioUsuarioJPA(repositorio, mapper);
    }


    @DisplayName("Deve criar um usuario")
    @Test
    void deveCriarUsuario() {
        try{
            String cpf = "123.456.789-10";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);

            FabricaUsuario fabricaUsuario = new FabricaUsuario();
            CriarUsuario criarUsuario = new CriarUsuario(repositorioUsuario);
            Usuario usuario  = criarUsuario.cadastrarUsuario(fabricaUsuario.comNomeCPFNascimento(nome, cpf, nascimento));
            Assertions.assertEquals(cpf, usuario.getCpf());

        }catch(IllegalArgumentException e){
            fail("Não deve lançar exceção");
        }
    }

    @DisplayName("Deve lancar uma exceção ao criar um usuario com cpf invalido")
    @Test
    void deveLançarExcecaoAoCriarUsuario() {
        try{
            String cpf = "123.456.789";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);

            FabricaUsuario fabricaUsuario = new FabricaUsuario();
            CriarUsuario criarUsuario = new CriarUsuario(repositorioUsuario);
            Usuario usuario  = criarUsuario.cadastrarUsuario(fabricaUsuario.comNomeCPFNascimento(nome, cpf, nascimento));
            Assertions.assertEquals(cpf, usuario.getCpf());
            fail("Deve lançar exceção");
        }catch(IllegalArgumentException e){
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("CPF no padrão errado!");
            });
        }
    }

}
