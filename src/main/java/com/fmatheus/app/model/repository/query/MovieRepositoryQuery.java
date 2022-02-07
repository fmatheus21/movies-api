package com.fmatheus.app.model.repository.query;

import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.repository.filter.RepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieRepositoryQuery {

    Page<Movie> findAllFilter(Pageable pageable, RepositoryFilter filter);

}
