package com.api.api.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.api.model.Usuario;
import com.api.api.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService{
    public UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
    }
    
    @Transactional
    @Override
    public Usuario update(Long id, Usuario ent) {
        if (!repo.existsById(id)) {
            throw new IllegalStateException("La entidad con el ID " + id + " no existe.");
        }
        Usuario existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id");
        return repo.save(existing);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = repo.findAll();
        if (usuarios.isEmpty()) {
            throw new IllegalStateException("No hay usuarios registrados.");
        }
        return usuarios;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> getById(Long id) {
        Optional<Usuario> usuario = repo.findById(id);
        if (usuario.isEmpty()) {
            throw new IllegalStateException("Usuario con ID " + id + " no encontrado.");
        }
        return usuario;
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalStateException("El usuario no puede estar vacio");
        }
        return repo.save(usuario);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalStateException("No se puede eliminar, usuario con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }
}
