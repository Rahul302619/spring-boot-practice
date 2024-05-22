package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepo extends JpaRepository<Term, Long> {
}
