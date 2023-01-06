package com.examplenative.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplenative.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String user);

}
