package com.fmatheus.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.storage.FileServiceStorage;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.entity.Movie;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    private int id;
    private String title;
    private int year;
    private BigDecimal rating;
    private String urlTrailer;
    private String image;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private String updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @Autowired
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private FileServiceStorage fileServiceStorage;


    public MovieDto converterToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(AppUtil.convertFirstUppercaseCharacter(movie.getTitle()))
                .year(movie.getYear())
                .rating(movie.getRating())
                .urlTrailer(movie.getUrlTrailer())
                .image(MethodGlobalUtil.converterImageToBase64(loadIcon(movie)))
                .createdBy(AppUtil.convertFirstUppercaseCharacter(movie.getCreatedBy().getPerson().getName()))
                .createdAt(movie.getCreatedAt())
                .updatedBy(AppUtil.convertFirstUppercaseCharacter(AppUtil.convertFirstUppercaseCharacter(movie.getUpdatedBy().getPerson().getName())))
                .updatedAt(movie.getUpdatedAt())
                .build();
    }

    private String loadIcon(Movie movie) {
        return fileServiceStorage.returnPath(MethodGlobalUtil.uploadFileConfig(UploadTypeEnum.MOVIE)).concat(File.separator).concat(movie.getImage());
    }


}
