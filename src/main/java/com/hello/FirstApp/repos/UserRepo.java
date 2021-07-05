package com.hello.FirstApp.repos;

import com.hello.FirstApp.modals.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query(value = "FROM User sc where sc.username = ?1")
    public Optional<User> findByUsername(String username);
}