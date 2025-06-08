package com.api.api.controller;
import com.api.api.model.Usuario;
import com.api.api.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/sign-it")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioSvc;

    public UsuarioControllerV2(UsuarioService usuarioSvc) {
        this.usuarioSvc = usuarioSvc;
    }


    @PostMapping
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        if (AuthenticatorController.verifyValidPassword(usuario.getPasswordHash()) && AuthenticatorController.verifyValidEmail(usuario.getEmail())) {
            usuario.setPasswordHash(AuthenticatorController.hashPassword(usuario.getPasswordHash()));
            return usuarioSvc.save(usuario);
        } else {
            throw new IllegalStateException("No se pude crear el usuario");
        }
    }
}
