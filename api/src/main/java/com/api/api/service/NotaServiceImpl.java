package com.api.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.api.model.Nota;
import com.api.api.repository.NotaRepository;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long> implements NotaService {
   public NotaServiceImpl(NotaRepository repo) {
        super(repo);
    }

    
    @Transactional
    @Override
    public Nota update(Long id, Nota ent) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("La entidad con el ID " + id + " no existe.");
        }
        Nota existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id");
        return repo.save(existing);
    }

}
