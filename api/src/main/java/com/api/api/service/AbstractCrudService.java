package com.api.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCrudService<T,ID> implements CrudService<T, ID> {

    protected final JpaRepository<T, ID> repo;

    public AbstractCrudService(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<T> getById(ID id) {
        return repo.findById(id);
    }

    @Override
    public T save(T ent) {
        return repo.save(ent);
    }

    @Transactional
    @Override
    public T update(ID id, T ent) {
        T existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id", "nota");
        return repo.save(existing);
    }

    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }

}
