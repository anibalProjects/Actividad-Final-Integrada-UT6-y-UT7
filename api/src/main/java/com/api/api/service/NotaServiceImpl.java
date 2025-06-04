package com.api.api.service;

import org.springframework.stereotype.Service;

import com.api.api.model.Nota;
import com.api.api.repository.NotaRepository;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long> implements NotaService {
   public NotaServiceImpl(NotaRepository repo) {
        super(repo);
    }
}
