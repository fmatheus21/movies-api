package com.fmatheus.app.model.service;

import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService extends SortService implements GenericService<Movie, Integer> {

    @Autowired
    private MovieRepository repository;

    @Override
    public List<Movie> findAllSortAsc(String attribute) {
        return this.repository.findAll(sortAsc(attribute));
    }

    @Override
    public List<Movie> findAllSortDesc(String attribute) {
        return this.repository.findAll(sortDesc(attribute));
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public <S extends Movie> S save(S s) {
        return this.repository.save(s);
    }

    @Override
    public <S extends Movie> Iterable<S> saveAll(Iterable<S> s) {
        return this.repository.saveAll(s);
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    public Optional<Movie> findByCodeImdb(String codeIndb) {
        return this.repository.findByCodeImdb(codeIndb);
    }
}
