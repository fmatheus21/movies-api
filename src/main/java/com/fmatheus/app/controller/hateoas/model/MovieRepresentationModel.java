package com.fmatheus.app.controller.hateoas.model;

import com.fmatheus.app.controller.dto.response.MovieDtoResponse;
import com.fmatheus.app.controller.enumerable.HateoasEnum;
import com.fmatheus.app.controller.resource.MovieResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class MovieRepresentationModel extends RepresentationModel<MovieDtoResponse> {

    public MovieDtoResponse hateoas(MovieDtoResponse movie, int id) {
        return movie.add(this.linkView(id));
    }

    private Link linkView(int id) {
        return linkTo(methodOn(MovieResource.class).findById(id))
                .withRel(HateoasEnum.VIEW.getValue())
                .withTitle(HateoasEnum.VIEW.getValue())
                .withType(HateoasEnum.VIEW_TYPE.getValue())
                .withSelfRel();
    }


}
