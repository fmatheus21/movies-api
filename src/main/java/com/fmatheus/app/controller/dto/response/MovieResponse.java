package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.dto.MovieDto;
import com.fmatheus.app.controller.hateoas.link.MovieLink;
import com.fmatheus.app.controller.storage.FilesStorageService;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"_links", "movie"})
public class MovieResponse extends RepresentationModel<MovieResponse> {

    private MovieDto movie;

    public static MovieResponse converterForResponse(Movie movie, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
        return hateoas(MovieResponse.builder()
                .movie(MovieDto.converterToDto(movie, methodGlobalUtil, filesStorageService))
                .build());
    }

    public static Collection<MovieResponse> converterListForResponse(Collection<Movie> collection, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
        return collection.stream().map(map -> converterForResponse(map, methodGlobalUtil, filesStorageService)).collect(Collectors.toUnmodifiableList());
    }

    private static MovieResponse hateoas(MovieResponse dto) {
        return new MovieLink().hateoas(dto, dto.getMovie().getId());
    }


}
