package com.api.api.service;

import com.api.api.model.Usuario;
import com.api.api.repository.UsuarioRepository;

public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService{
    public UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
    }
}
