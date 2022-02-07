package com.fmatheus.app.model.repository.impl;

import com.fmatheus.app.controller.enumerable.EntityEnum;
import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.repository.filter.RepositoryFilter;
import com.fmatheus.app.model.repository.page.RestrictionPageImpl;
import com.fmatheus.app.model.repository.query.MovieRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieRepositoryImpl extends RestrictionPageImpl<Movie> implements MovieRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    protected MovieRepositoryImpl(EntityManager manager) {
        super(manager, Movie.class);
    }

    @Override
    public Page<Movie> findAllFilter(Pageable pageable, RepositoryFilter filter) {

        var builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = builder.createQuery(Movie.class);
        Root<Movie> root = criteriaQuery.from(Movie.class);
        var predicates = this.createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates).orderBy(builder.asc(root.get(EntityEnum.TITLE.getValue())));
        TypedQuery<Movie> typedQuery = this.manager.createQuery(criteriaQuery);
        this.addPageRestrictions(typedQuery, pageable);
        return new PageImpl<>(typedQuery.getResultList(), pageable, this.total(filter, builder, root));
    }

    private Predicate[] createRestrictions(RepositoryFilter filter, CriteriaBuilder builder, Root<Movie> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getCodeImdb())) {
            predicates.add(builder.like(builder.lower(root.get(EntityEnum.CODE_IMDB.getValue())), "%" + filter.getCodeImdb().toLowerCase() + "%"));
        }

        if (Objects.nonNull(filter.getTitle())) {
            predicates.add(builder.like(builder.lower(root.get(EntityEnum.TITLE.getValue())), "%" + filter.getTitle().toLowerCase() + "%"));
        }

        if (Objects.nonNull(filter.getYear())) {
            predicates.add(builder.equal(root.get(EntityEnum.YEAR.getValue()), filter.getYear()));
        }

        if (Objects.nonNull(filter.getRating())) {
            predicates.add(builder.like(builder.lower(root.get(EntityEnum.RATING.getValue())), "%" + filter.getRating() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private Long total(RepositoryFilter filter, CriteriaBuilder builder, Root<Movie> root) {
        Predicate[] predicates = createRestrictions(filter, builder, root);
        return total(filter, predicates);
    }

}
