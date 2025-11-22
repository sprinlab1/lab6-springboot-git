package com.example.lab5blog;

import com.example.lab5blog.model.Post;
import com.example.lab5blog.repo.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

@Testcontainers
@SpringBootTest
class PostRepositoryIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private PostRepository postRepository;

    @Test
    void testSaveAndLoadPost() {
        Post post = new Post();
        post.setTitle("Testcontainers post");
        post.setContent("Це пост, створений у інтеграційному тесті.");
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);

        Iterable<Post> all = postRepository.findAll();
        boolean hasAny = all.iterator().hasNext();

        Assertions.assertTrue(hasAny, "У базі даних має бути хоча б один пост");
    }
}
