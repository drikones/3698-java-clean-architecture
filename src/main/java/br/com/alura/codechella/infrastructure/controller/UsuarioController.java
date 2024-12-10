package br.com.alura.codechella.infrastructure.controller;

import br.com.alura.codechella.application.usecases.AlterarUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.ExcluirUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final AlterarUsuario alterarUsuario;
    private final ExcluirUsuario excluirUsuario;;

    public UsuarioController(CriarUsuario criarUsuario, ListarUsuarios listarUsuarios, AlterarUsuario alterarUsuario, ExcluirUsuario excluirUsuario) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
        this.alterarUsuario = alterarUsuario;
        this.excluirUsuario = excluirUsuario;
    }

    @PostMapping
    public UsuarioDTO cadastrar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = this.criarUsuario.cadastrarUsuario(new Usuario(dto.cpf(), dto.nome(),dto.nascimento(), dto.email()));
        return new UsuarioDTO(usuario.getCpf(), usuario.getNome(), usuario.getNascimento(), usuario.getEmail());
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return listarUsuarios.listarTodos().stream().map(u -> new UsuarioDTO(u.getCpf(), u.getNome(), u.getNascimento(), u.getEmail())).toList();
    }

    @PutMapping("/{cpf}")
    public UsuarioDTO alterar(@PathVariable String cpf, @RequestBody UsuarioDTO dto) {
        Usuario usuario = this.alterarUsuario.alterarUsuario(cpf, new Usuario(dto.cpf(), dto.nome(),dto.nascimento(), dto.email()));
        return new UsuarioDTO(usuario.getCpf(), usuario.getNome(), usuario.getNascimento(), usuario.getEmail());
    }

    @DeleteMapping("/{cpf}")
    public void excluir(@PathVariable String cpf) {
        this.excluirUsuario.excluirUsuario(cpf);
    }

}
