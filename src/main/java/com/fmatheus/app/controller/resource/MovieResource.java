package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.constant.HttpStatusConstant;
import com.fmatheus.app.controller.constant.OperationConstant;
import com.fmatheus.app.controller.constant.ResourceConstant;
import com.fmatheus.app.controller.dto.response.MovieResponse;
import com.fmatheus.app.controller.dto.swagger.*;
import com.fmatheus.app.controller.rule.MovieRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(ResourceConstant.MOVIES)
public class MovieResource {

    @Autowired
    private MovieRule rule;

    @Operation(summary = OperationConstant.LIST, description = OperationConstant.DESCRIPTION_LIST,
            tags = {OperationConstant.TAG_MOVIE})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusConstant.OK_NUMBER, description = HttpStatusConstant.OK,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = HttpStatusConstant.NO_CONTENT_NUMBER, description = HttpStatusConstant.NO_CONTENT,
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = HttpStatusConstant.UNAUTHORIZED_NUMBER, description = HttpStatusConstant.UNAUTHORIZED,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unauthorized.class))),
            @ApiResponse(responseCode = HttpStatusConstant.FORBIDDEN_NUMBER, description = HttpStatusConstant.FORBIDDEN,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forbidden.class))),
            @ApiResponse(responseCode = HttpStatusConstant.NOT_FOUND_NUMBER, description = HttpStatusConstant.NOT_FOUND,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notfound.class))),
            @ApiResponse(responseCode = HttpStatusConstant.INTERNAL_SERVER_ERROR_NUMBER, description = HttpStatusConstant.INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerError.class)))
    })
    @GetMapping
    public ResponseEntity<Collection<MovieResponse>> findAll() {
        return rule.findAll();
    }

    @Operation(summary = OperationConstant.GET, description = OperationConstant.DESCRIPTION_GET,
            tags = {OperationConstant.TAG_MOVIE})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusConstant.OK_NUMBER, description = HttpStatusConstant.OK,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = HttpStatusConstant.BAD_REQUEST_NUMBER, description = HttpStatusConstant.BAD_REQUEST,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class))),
            @ApiResponse(responseCode = HttpStatusConstant.UNAUTHORIZED_NUMBER, description = HttpStatusConstant.UNAUTHORIZED,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unauthorized.class))),
            @ApiResponse(responseCode = HttpStatusConstant.FORBIDDEN_NUMBER, description = HttpStatusConstant.FORBIDDEN,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forbidden.class))),
            @ApiResponse(responseCode = HttpStatusConstant.INTERNAL_SERVER_ERROR_NUMBER, description = HttpStatusConstant.INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerError.class)))
    })
    @GetMapping(ResourceConstant.ID)
    public ResponseEntity<MovieResponse> findById(@PathVariable int id) {
        return rule.findById(id);
    }

}
