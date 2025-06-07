package com.api.api.service;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.api.api.model.Nota;


public interface NotaService extends CrudService<Nota, Long> {
    List<Nota> searchUsuarios(Long id, Sort sort);
    List<Nota> searchNotas(Long id, String titulo, String contenido, Sort sort);
}
