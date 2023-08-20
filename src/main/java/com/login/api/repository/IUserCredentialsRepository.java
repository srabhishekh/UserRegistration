package com.login.api.repository;

import com.login.api.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
}
