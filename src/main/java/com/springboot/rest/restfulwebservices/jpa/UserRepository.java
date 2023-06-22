package com.springboot.rest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
