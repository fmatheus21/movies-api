package com.fmatheus.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.storage.FilesStorageService;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "title", "year", "rating", "urlTrailer", "createdBy", "createdAt", "updatedBy", "updatedAt", "image"})
public class MovieDto {

    private int id;
    private String title;
    private int year;
    private BigDecimal rating;
    private String urlTrailer;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private String updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    private String image;


    public static MovieDto converterToDto(Movie movie, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
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

    @SneakyThrows
    private static String loadIcon(Movie movie, MethodGlobalUtil methodGlobalUtil, FilesStorageService filesStorageService) {
        return filesStorageService.load(methodGlobalUtil.uploadFileConfig(UploadTypeEnum.MOVIE, movie.getImage())).getFile().getAbsolutePath();
    }


}
