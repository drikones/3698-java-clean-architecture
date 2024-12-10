package br.com.alura.codechella.infrastructure.gateways;

import br.com.alura.codechella.application.gateways.RepositorioUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infrastructure.persistence.UsuarioEntity;
import br.com.alura.codechella.infrastructure.persistence.UsuarioRepository;

import java.util.List;

public class RepositorioUsuarioJPA implements RepositorioUsuario {

    private final UsuarioRepository repositorio;
    private final UsuarioEntityMapper mapper;

    public RepositorioUsuarioJPA(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = this.mapper.toEntity(usuario);
        repositorio.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        List<UsuarioEntity> entities = repositorio.findAll();
        return mapper.toListDomain(entities);
    }

    @Override
    public Usuario alteraUsuario(String cpf, Usuario usuario) {
        UsuarioEntity entity = repositorio.findByCpf(cpf);
        if(entity != null){
            var entityAlterada = mapper.toEntity(usuario);
            repositorio.save(entityAlterada);
            return mapper.toDomain(entityAlterada);
        }
        return null;
    }

    @Override
    public void excluiUsuario(String cpf) {
        UsuarioEntity entity = repositorio.findByCpf(cpf);
        if(entity != null){
            repositorio.deleteByCpf(entity.getCpf());
        }
    }
}
