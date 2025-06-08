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

import org.springframework.http.ResponseEntity;
import jakarta.validation.constraints.Positive;


@RestController
@RequestMapping("/api/v1/Usuario")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioSvc;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioSvc) {
        this.usuarioSvc = usuarioSvc;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioSvc.getAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id) {
        return usuarioSvc.getById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró el usuario con ID: " + id));
    }

    @PostMapping
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        return usuarioSvc.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable @Positive(message = "El id debe ser un número positivo") Long id, @RequestBody @Valid Usuario usuario) {
        try {
            logger.info("Intento de actualizar usuario, id: {}", id);
            logger.info("Datos: {}", usuario);
            
            if (usuario == null) {
                throw new IllegalStateException("El usuario no puede estar vacío");
            }
            
            if (!usuarioSvc.getById(id).isPresent()) {
                throw new IllegalStateException("No se encontró el usuario con id: " + id);
            }
            
            Usuario updatedUsuario = usuarioSvc.update(id, usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (Exception e) {
            logger.error("Error al actualizar usuario: ", e);
            return ResponseEntity.internalServerError().body("Error al actualizar usuario: " + e.getMessage());
        }
    }       

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = "El id debe ser un número positivo") Long id) {
        if (!usuarioSvc.getById(id).isPresent()) {
            logger.info("Intento de eliminación de usuario fallido: {}", id);
            throw new IllegalStateException("No se encontró el usuario con id: " + id);
        }
        logger.info("Usuario eliminado, id: {}", id);
        usuarioSvc.deleteById(id);
    }
}