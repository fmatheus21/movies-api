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
public class MovieDtoResponse extends RepresentationModel<MovieDtoResponse> {

    private MovieDto movie;

    @Autowired
    private MovieConverter converter;

    public MovieDtoResponse converterForResponse(Movie movie) {
        return this.hateoas(MovieDtoResponse.builder()
                .movie(this.converter.converterToDto(movie))
                .build());
    }

    private MovieDtoResponse hateoas(MovieDtoResponse dto) {
        return new MovieRepresentationModel().hateoas(dto, dto.getMovie().getId());
    }

    public Page<MovieDtoResponse> converterListForResponse(Page<Movie> movies) {
        return movies.map(this::converterForResponse);
    }


}
