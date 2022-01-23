package com.fmatheus.app.controller.hateoas.link;

import com.fmatheus.app.controller.dto.response.MovieResponse;
import com.fmatheus.app.controller.enumerable.HateoasEnum;
import com.fmatheus.app.controller.resource.MovieResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class MovieLink extends RepresentationModel<MovieResponse> {

    public MovieResponse hateoas(MovieResponse movie, int id) {
        return movie.add(this.linkView(id));
    }

    private Link linkView(int id) {
        return linkTo(methodOn(MovieResource.class).findById(id))
                .withRel(HateoasEnum.VIEW.getValue())
                .withTitle(HateoasEnum.VIEW.getValue() + HateoasEnum.RECORD.getValue())
                .withType(HateoasEnum.VIEW_TYPE.getValue())
                .withSelfRel();
    }


}