package com.springboot.rest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.restfulwebservices.user.Posts;

public interface PostRepository extends JpaRepository<Posts, Integer> {

}
