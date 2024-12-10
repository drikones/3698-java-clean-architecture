package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {


    private Usuario usuario;


    @DisplayName("lança exceção ao criar usuário com cpf inválido")
    @Test
    public void testaCpfExcecao() {
        try{
            String cpf = "123.456.789";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);
            String email = "fulano@gmail.com";
            this.usuario = new Usuario(cpf, nome, nascimento, email);
            fail("Deveria ter lançado exceção");
        }catch(IllegalArgumentException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw e;
            });
        }
    }

    @DisplayName("lança exceção ao criar usuário com cpf nulo")
    @Test
    public void testaCpfNulo() {
        try{
            String cpf = null;
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);
            String email = "fulano@gmail.com";
            this.usuario = new Usuario(cpf, nome, nascimento, email);
            fail("Deveria ter lançado exceção");
        }catch(IllegalArgumentException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw e;
            });
        }
    }

    @DisplayName("cadastro realizado com sucesso")
    @Test
    public void testaCpf() {
        try{
            String cpf = "123.456.789-10";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);
            String email = "fulano@gmail.com";
            this.usuario = new Usuario(cpf, nome, nascimento, email);
        }catch(IllegalArgumentException e) {
            fail("Deveria ter lançado exceção");
        }
    }

    @DisplayName("Deve criar usuario com a fábrica passando nome, cpf e nascimento")
    @Test
    public void testaFabricaUsuario(){
        try{
            String cpf = "123.456.789-10";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);
            this.usuario  = new FabricaUsuario().comNomeCPFNascimento(nome, cpf, nascimento);
            assertEquals(this.usuario.getCpf(), cpf);
        }catch(IllegalArgumentException e) {
            fail("Deveria ter lançado exceção");
        }
    }

    @DisplayName("Deve criar usuario com a fábrica incluindo endereco")
    @Test
    public void testaFabricaUsuarioSetarEndereco(){
        try{
            String cep = "06449-130";
            Integer numero = 102;
            String complemento = "apto 101";
            String cpf = "123.456.789-10";
            String nome = "Fulano";
            LocalDate nascimento = LocalDate.of(2000, 1, 1);

            FabricaUsuario fabrica = new FabricaUsuario();

            this.usuario  = fabrica.comNomeCPFNascimento(nome, cpf, nascimento);
            this.usuario = fabrica.incluiEndereco(cep, numero, complemento);

            Assertions.assertEquals("apto 101", this.usuario.getEndereco().getComplemento());
        }catch(IllegalArgumentException e) {
            fail("Deveria ter lançado exceção");
        }
    }

}
