package com.api.api.controller;
import com.api.api.model.Nota;
import com.api.api.service.NotaService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/notas")
public class NotasController {

    private final NotaService notaService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioControllerV2.class);

    public NotasController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAll(){
        List<Nota> resultados = notaService.getAll();
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id}")
    public Nota getById(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id) {
        return notaService.getById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró la nota con ID: " + id));
    }

    @PostMapping
    public Nota create(@RequestBody @Valid Nota nota) {
        nota.setFechaCreacion(LocalDateTime.now());
        return notaService.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id, @RequestBody Nota nota) {
        if (!notaService.getById(id).isPresent()) {
            logger.info("No se encontró la nota, id: {}", id);
            throw new IllegalStateException("No se encontró la nota con ID: " + id);
        }
        nota.setId(id);
        return notaService.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id) {
        if (!notaService.getById(id).isPresent()) {
            logger.info("No se encontró la nota, id: {}", id);
            throw new IllegalStateException("No se encontró la nota con ID: " + id);
        }
        notaService.deleteById(id);
    }

    
    @GetMapping("/searchUserNote")
    public ResponseEntity<List<Nota>> search(
            @RequestParam(required = true) @Positive(message = "El ID del usuario debe ser un número positivo") Long usuarioId,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction dir = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(dir, sortBy);

        List<Nota> resultados = notaService.searchUsuarios(usuarioId, sort);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Nota>> searchNotas(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String contenido,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction dir = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(dir, sortBy);
        List<Nota> notas = notaService.searchNotas(id, titulo, contenido, sort);
        return ResponseEntity.ok(notas);
    }
}
