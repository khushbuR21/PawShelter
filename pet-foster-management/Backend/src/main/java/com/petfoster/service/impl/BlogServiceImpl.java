package com.petfoster.service.impl;

import com.petfoster.dto.blog.*;
import com.petfoster.entity.BlogPost;
import com.petfoster.entity.User;
import com.petfoster.repository.BlogRepository;
import com.petfoster.repository.UserRepository;
import com.petfoster.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BlogResponseDTO createBlog(
            BlogCreateDTO dto, String authorEmail) {

        User author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BlogPost blog = BlogPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .createdAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .build();

        BlogPost saved = blogRepository.save(blog);
        return mapToResponse(saved);
    }

    @Override
    public List<BlogResponseDTO> getMyBlogs(String authorEmail) {

        User author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        return blogRepository.findByAuthorId(author.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogResponseDTO> getAllBlogs() {

        return blogRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BlogResponseDTO mapToResponse(BlogPost blog) {

        return BlogResponseDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorEmail(blog.getAuthor().getEmail())
                .createdAt(blog.getCreatedAt())
                .publishedAt(blog.getPublishedAt())
                .build();
    }
}
