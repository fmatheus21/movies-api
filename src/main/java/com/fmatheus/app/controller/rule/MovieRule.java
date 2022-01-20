package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.dto.response.MovieResponse;
import com.fmatheus.app.controller.enumerable.EntityEnum;
import com.fmatheus.app.model.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class MovieRule {

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieResponse movieResponse;


    public ResponseEntity<Collection<MovieResponse>> findAll() {
        var movies = this.movieService.findAllSortAsc(EntityEnum.TITLE.getValue());
        return Objects.nonNull(movies) ? ResponseEntity.ok(this.movieResponse.converterListForResponse(movies)) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<MovieResponse> findById(int id) {
        var movie = this.movieService.findById(id).orElseThrow(
                () -> this.messageResponseRule.errorNotFound()
        );
        return ResponseEntity.status(HttpStatus.OK).body(movieResponse.converterForResponse(movie));
    }


}
