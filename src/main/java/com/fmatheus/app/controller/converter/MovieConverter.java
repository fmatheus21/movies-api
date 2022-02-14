package com.fmatheus.app.controller.converter;

import com.fmatheus.app.controller.dto.MovieDto;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.storage.FilesStorageService;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.entity.Movie;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class MovieConverter {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private MethodGlobalUtil methodGlobalUtil;

    public MovieDto converterToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(AppUtil.convertFirstUppercaseCharacter(movie.getTitle()))
                .year(movie.getYear())
                .rating(movie.getRating())
                .urlTrailer(movie.getUrlTrailer())
                .image(MethodGlobalUtil.converterImageToBase64(loadIcon(movie, methodGlobalUtil, filesStorageService)))
                .createdBy(AppUtil.convertFirstUppercaseCharacter(movie.getCreatedBy().getPerson().getName()))
                .createdAt(movie.getCreatedAt())
                .updatedBy(AppUtil.convertFirstUppercaseCharacter(AppUtil.convertFirstUppercaseCharacter(movie.getUpdatedBy().getPerson().getName())))
                .updatedAt(movie.getUpdatedAt())
                .build();
    }

    public Page<MovieDto> converterToListDto(Page<Movie> movies) {
        return movies.map(this::converterToDto);
    }

    @SneakyThrows
    private String loadIcon(Movie movie, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
        return filesStorageService.load(methodGlobalUtil.uploadFileConfig(UploadTypeEnum.MOVIE, movie.getImage())).getFile().getAbsolutePath();
    }

}
