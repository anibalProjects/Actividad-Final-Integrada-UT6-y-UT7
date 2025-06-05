package com.api.api.controller;
import com.api.api.model.Nota;
import com.api.api.repository.NotaRepository;
import com.api.api.service.NotaService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/notas")
public class NotasController {

    private final NotaService notaService;

    public NotasController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAll(
            @RequestParam(required = false) @Positive(message = "El ID del usuario debe ser un número positivo") Long usuarioId,
            @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction dir = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sort = Sort.by(dir, "id");

        List<Nota> resultados = notaService.searchUsuarios(usuarioId, sort);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id}")
    public Nota getById(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id) {
        return notaService.getById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró la nota con ID: " + id));
    }

    @PostMapping
    public Nota create(@RequestBody @Valid Nota nota) {
        return notaService.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id, @RequestBody Nota nota) {
        if (!notaService.getById(id).isPresent()) {
            throw new IllegalStateException("No se encontró la nota con ID: " + id);
        }
        nota.setId(id);
        return notaService.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = "El ID debe ser un número positivo") Long id) {
        if (!notaService.getById(id).isPresent()) {
            throw new IllegalStateException("No se encontró la nota con ID: " + id);
        }
        notaService.deleteById(id);
    }

    
    @GetMapping("/buscar")
    public ResponseEntity<List<Nota>> search(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction dir = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sort = Sort.by(dir, sortBy);

        List<Nota> resultados = notaService.searchUsuarios(id, sort);
        return ResponseEntity.ok(resultados);
    }
}
