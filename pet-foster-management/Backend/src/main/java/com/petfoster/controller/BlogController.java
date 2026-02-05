package com.petfoster.controller;

import com.petfoster.dto.blog.*;
import com.petfoster.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // CUSTOMER
    @PostMapping
    public ResponseEntity<BlogResponseDTO> create(
            @Valid @RequestBody BlogCreateDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                blogService.createBlog(dto, authentication.getName()));
    }

    // CUSTOMER
    @GetMapping("/my")
    public ResponseEntity<List<BlogResponseDTO>> myBlogs(
            Authentication authentication) {

        return ResponseEntity.ok(
                blogService.getMyBlogs(authentication.getName()));
    }

    // ADMIN + CUSTOMER
    @GetMapping
    public ResponseEntity<List<BlogResponseDTO>> allBlogs() {

        return ResponseEntity.ok(
                blogService.getAllBlogs());
    }
}
