package com.api.api.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.api.model.Nota;
import com.api.api.repository.NotaRepository;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long> implements NotaService {
    private final NotaRepository repo;
   public NotaServiceImpl(NotaRepository repo) {
       super(repo);
        this.repo = repo;
    }

    
    @Transactional(readOnly = true)
    @Override
    public List<Nota> searchUsuarios(Long usuarioId, Sort sort) {
        if (usuarioId != null) {
            return repo.findByUsuarioId(usuarioId, sort);
        }
        return repo.findAll(sort);
    }

    @Transactional
    @Override
    public Nota update(Long id, Nota ent) {
        if (!repo.existsById(id)) {
            throw new IllegalStateException("La entidad con el ID " + id + " no existe.");
        }
        Nota existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id");
        return repo.save(existing);
    }


  


    

}
