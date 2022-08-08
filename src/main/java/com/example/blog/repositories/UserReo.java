package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entities.User;

@Repository
public interface UserReo extends JpaRepository<User, Integer> {

}
