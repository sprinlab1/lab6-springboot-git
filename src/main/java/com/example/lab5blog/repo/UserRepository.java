package com.example.lab5blog.repo;

import com.example.lab5blog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
