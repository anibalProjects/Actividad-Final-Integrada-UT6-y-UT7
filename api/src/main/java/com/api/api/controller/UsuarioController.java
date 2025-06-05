package com.api.api.controller;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api.model.Usuario;
import com.api.api.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/Usuario")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioSvc;


    public UsuarioController(UsuarioService usuarioSvc) {
        this.usuarioSvc = usuarioSvc;
    }

    // Listar usuario
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioSvc.getAll();
    }

    // Listar usuario por ID
    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioSvc.getById(id).orElse(null);
    }

    // Crear usuario
    @PostMapping
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        return usuarioSvc.save(usuario);
    }

    // Editar Usuario por ID
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
       return usuarioSvc.update(id, usuario);
    }

    // Borrar Usuario por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioSvc.deleteById(id);
    }

    @GetMapping("id")
    public Usuario update(@RequestParam Long id) {
        return this.usuarioSvc.update(id, null);
    }
    
}