package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findByUserName(String userName);
}
