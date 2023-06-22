package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.*;

public interface UserRepository extends CrudRepository<User, Integer>{
    @Query(value="select * from users where email = ?1", nativeQuery=true)
    User findByEmail(String email);
}
