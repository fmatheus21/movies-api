package com.fmatheus.app.model.service;

import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService extends SortService implements GenericService<User, Integer> {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAllSortAsc(String attribute) {
        return this.repository.findAll(sortAsc(attribute));
    }

    @Override
    public List<User> findAllSortDesc(String attribute) {
        return this.repository.findAll(sortDesc(attribute));
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public <S extends User> S save(S s) {
        return this.repository.save(s);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> s) {
        return this.repository.saveAll(s);
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
