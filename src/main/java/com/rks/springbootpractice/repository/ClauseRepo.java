package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.Clause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClauseRepo extends JpaRepository<Clause, Long> {
}
