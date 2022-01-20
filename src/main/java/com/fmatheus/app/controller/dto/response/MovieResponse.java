package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmatheus.app.controller.dto.MovieDto;
import com.fmatheus.app.controller.hateoas.link.MovieLink;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse extends RepresentationModel<MovieResponse> {

    @JsonProperty("movie")
    @Autowired
    private MovieDto movieDto;

    public MovieResponse converterForResponse(Movie movie) {
        return hateoas(MovieResponse.builder()
                .movieDto(this.movieDto.converterToDto(movie))
                .build());
    }

    public Collection<MovieResponse> converterListForResponse(Collection<Movie> collection) {
        return collection.stream().map(this::converterForResponse).collect(Collectors.toUnmodifiableList());
    }

    private MovieResponse hateoas(MovieResponse dto) {
        return new MovieLink().hateoas(dto, dto.getMovieDto().getId());
    }


}
