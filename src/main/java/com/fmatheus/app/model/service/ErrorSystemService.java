package com.fmatheus.app.model.service;

import com.fmatheus.app.model.entity.ErrorSystem;
import com.fmatheus.app.model.repository.ErrorSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ErrorSystemService extends SortService implements GenericService<ErrorSystem, Integer> {

    @Autowired
    private ErrorSystemRepository repository;

    @Override
    public List<ErrorSystem> findAllSortAsc(String attribute) {
        return this.repository.findAll(sortAsc(attribute));
    }

    @Override
    public List<ErrorSystem> findAllSortDesc(String attribute) {
        return this.repository.findAll(sortDesc(attribute));
    }

    @Override
    public Optional<ErrorSystem> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public <S extends ErrorSystem> S save(S s) {
        return this.repository.save(s);
    }

    @Override
    public <S extends ErrorSystem> Iterable<S> saveAll(Iterable<S> s) {
        return this.repository.saveAll(s);
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }
}
