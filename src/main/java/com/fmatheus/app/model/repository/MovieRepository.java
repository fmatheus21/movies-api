package com.fmatheus.app.model.repository;

import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.repository.query.MovieRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, MovieRepositoryQuery {

    Optional<Movie> findByCodeImdb(String codeIndb);

}