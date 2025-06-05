package com.api.api.service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.api.api.model.Usuario;
import com.api.api.repository.UsuarioRepository;
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService{
    public UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
    }
    
    @Transactional
    @Override
    public Usuario update(Long id, Usuario ent) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("La entidad con el ID " + id + " no existe.");
        }
        Usuario existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id");
        return repo.save(existing);
    }

}
