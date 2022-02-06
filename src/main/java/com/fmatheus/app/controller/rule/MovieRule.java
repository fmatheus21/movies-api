package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.dto.request.MovieRequest;
import com.fmatheus.app.controller.dto.response.MovieResponse;
import com.fmatheus.app.controller.enumerable.EntityEnum;
import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.enumerable.UploadTypeEnum;
import com.fmatheus.app.controller.exception.handler.response.MessageResponse;
import com.fmatheus.app.controller.storage.FilesStorageService;
import com.fmatheus.app.controller.util.AuthUtil;
import com.fmatheus.app.controller.util.MethodGlobalUtil;
import com.fmatheus.app.model.service.MovieService;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Objects;

@Component
public class MovieRule {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieResponse movieResponse;

    @Autowired
    private FilesStorageService storageService;

    @Autowired
    private MethodGlobalUtil methodGlobalUtil;


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


    @SneakyThrows
    public ResponseEntity<MessageResponse> create(Authentication auth, String json, MultipartFile file) {

        var objectMapper = new ObjectMapper();
        var movieRequest = objectMapper.readValue(json, MovieRequest.class);

        var movie = this.movieService.findByCodeImdb(movieRequest.getCodeImdb()).orElse(null);

        if (Objects.nonNull(movie)) {
            throw this.messageResponseRule.errorBadRequest(MessagesEnum.ERROR_CODE_IMDB_EXIST);
        }

        var user = this.authUtil.findByUsername(auth);

        if (Objects.isNull(file)) {
            throw this.messageResponseRule.badRequestErrorFileMaxLength();
        }

        var image = this.saveFile(file);
        var request = MovieRequest.converterEntity(movieRequest, image, user);
        this.movieService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageResponseRule.messageSuccessCreate());

    }


    private String saveFile(MultipartFile file) {
        var fileResponse = this.methodGlobalUtil.uploadFileConfig(UploadTypeEnum.MOVIE, null);
        return this.storageService.save(file, fileResponse);
    }


}
