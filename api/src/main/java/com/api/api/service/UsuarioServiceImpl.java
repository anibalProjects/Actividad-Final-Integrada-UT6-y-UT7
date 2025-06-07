package com.api.api.service;

import java.util.NoSuchElementException;

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
        try {
            return super.update(id, ent);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("El usuario con el ID " + id + " no existe.");
        }
    }
}
