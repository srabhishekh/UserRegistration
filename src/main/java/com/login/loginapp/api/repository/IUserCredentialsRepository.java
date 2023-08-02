package com.login.loginapp.api.repository;

import com.login.loginapp.api.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
}
