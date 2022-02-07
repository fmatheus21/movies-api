package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.dto.MovieDto;
import com.fmatheus.app.controller.hateoas.model.MovieRepresentationModel;
import com.fmatheus.app.controller.storage.FilesStorageService;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

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

    public static Page<MovieResponse> converterListForResponse(Page<Movie> movies, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
        return movies.map(map -> MovieResponse.converterForResponse(map, methodGlobalUtil, filesStorageService));
    }

    private static MovieResponse hateoas(MovieResponse dto) {
        return new MovieRepresentationModel().hateoas(dto, dto.getMovie().getId());
    }


}
