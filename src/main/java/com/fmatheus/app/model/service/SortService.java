package com.fmatheus.app.model.service;

import org.springframework.data.domain.Sort;


public class SortService {

    public Sort sortAsc(String attribute) {
        return Sort.by(Sort.Direction.ASC, attribute);
    }

    public Sort sortDesc(String attribute) {
        return Sort.by(Sort.Direction.DESC, attribute);
    }

}
