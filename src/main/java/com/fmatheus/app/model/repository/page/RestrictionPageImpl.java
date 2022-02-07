package com.fmatheus.app.model.repository.page;

import com.fmatheus.app.model.repository.filter.RepositoryFilter;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RestrictionPageImpl<T> implements RestrictionPage<T> {

    private final EntityManager manager;
    private final Class<T> clazz;

    protected RestrictionPageImpl(EntityManager manager, Class<T> clazz) {
        this.manager = manager;
        this.clazz = clazz;
    }

    @Override
    public void addPageRestrictions(TypedQuery<T> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;
        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

    @Override
    public Long total(RepositoryFilter filter, Predicate[] predicates) {
        var builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(this.clazz);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return this.manager.createQuery(criteriaQuery).getSingleResult();
    }

}
