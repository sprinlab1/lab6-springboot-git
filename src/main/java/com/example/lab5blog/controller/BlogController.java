package com.example.lab5blog.controller;

import com.example.lab5blog.dto.PostForm;
import com.example.lab5blog.model.Post;
import com.example.lab5blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class BlogController {

    private final PostRepository postRepository;

    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("postForm", new PostForm());
        return "posts";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute("postForm") PostForm form) {
        if (form.getTitle() != null && !form.getTitle().isBlank()
                && form.getContent() != null && !form.getContent().isBlank()) {
            Post post = new Post();
            post.setTitle(form.getTitle());
            post.setContent(form.getContent());
            post.setCreatedAt(LocalDateTime.now());
            postRepository.save(post);
        }
        return "redirect:/posts";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/posts";
    }
}
