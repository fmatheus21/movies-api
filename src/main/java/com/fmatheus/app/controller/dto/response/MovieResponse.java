package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.converter.MovieConverter;
import com.fmatheus.app.controller.dto.MovieDto;
import com.fmatheus.app.controller.hateoas.model.MovieRepresentationModel;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;


@Component
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"converter"})
@JsonPropertyOrder({"_links", "movie"})
public class MovieResponse extends RepresentationModel<MovieResponse> {

    private MovieDto movie;

    @Autowired
    private MovieConverter converter;

    public MovieResponse converterForResponse(Movie movie) {
        return this.hateoas(MovieResponse.builder()
                .movie(this.converter.converterToDto(movie))
                .build());
    }

    private MovieResponse hateoas(MovieResponse dto) {
        return new MovieRepresentationModel().hateoas(dto, dto.getMovie().getId());
    }

    public Page<MovieResponse> converterListForResponse(Page<Movie> movies) {
        return movies.map(this::converterForResponse);
    }


}
