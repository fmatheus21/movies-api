package com.fmatheus.app.model.repository;

import com.fmatheus.app.model.entity.ErrorSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorSystemRepository extends JpaRepository<ErrorSystem, Integer> {
}