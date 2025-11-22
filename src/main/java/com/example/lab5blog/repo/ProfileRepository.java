package com.example.lab5blog.repo;

import com.example.lab5blog.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
