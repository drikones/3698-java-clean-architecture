package br.com.alura.codechella.infrastructure.gateways;

import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infrastructure.persistence.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario usuario){
        return new UsuarioEntity(usuario.getCpf(), usuario.getNome(),
                usuario.getNascimento(), usuario.getEmail());
    }

    public Usuario toDomain(UsuarioEntity entity){
        return new Usuario(entity.getCpf(), entity.getNome(), entity.getNascimento(),
                entity.getEmail());
    }

    public List<Usuario> toListDomain(List<UsuarioEntity> entities){
        List<Usuario> usuarios = new ArrayList<>();
        entities.forEach(entity -> {
            usuarios.add(toDomain(entity));
        });
        return usuarios;
    }

}
