package com.fmatheus.app.model.repository.filter;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryFilter {

    private String codeImdb;
    private String title;
    private BigDecimal rating;
    private Integer year;

}
