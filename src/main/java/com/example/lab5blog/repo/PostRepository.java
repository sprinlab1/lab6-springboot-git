package com.example.lab5blog.repo;

import com.example.lab5blog.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
