package com.petfoster.service;

import com.petfoster.dto.blog.BlogCreateDTO;
import com.petfoster.dto.blog.BlogResponseDTO;

import java.util.List;

public interface BlogService {

    BlogResponseDTO createBlog(BlogCreateDTO dto, String authorEmail);

    List<BlogResponseDTO> getMyBlogs(String authorEmail);

    List<BlogResponseDTO> getAllBlogs();
}
