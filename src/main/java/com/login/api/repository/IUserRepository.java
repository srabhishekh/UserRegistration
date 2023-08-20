package com.login.api.repository;

import com.login.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
    User findByUserName(String userName);
}
