package com.fmatheus.app.model.repository.page;

import com.fmatheus.app.model.repository.filter.RepositoryFilter;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;

public interface RestrictionPage<T> {

    void addPageRestrictions(TypedQuery<T> typedQuery, Pageable pageable);

    Long total(RepositoryFilter filter, Predicate[] predicates);

}
