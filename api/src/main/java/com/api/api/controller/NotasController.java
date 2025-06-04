package com.api.api.controller;

import com.api.api.model.Nota;
import com.api.api.repository.NotaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
public class NotasController {

    private final NotaRepository notaRepo;

    public NotasController(NotaRepository notaRepo) {
        this.notaRepo = notaRepo;
    }

    @GetMapping
    public List<Nota> getAll() {
        return notaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Nota getById(@PathVariable Long id) {
        return notaRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Nota create(@RequestBody Nota nota) {
        return notaRepo.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Long id, @RequestBody Nota nota) {
        nota.setId(id);
        return notaRepo.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notaRepo.deleteById(id);
    }
}
