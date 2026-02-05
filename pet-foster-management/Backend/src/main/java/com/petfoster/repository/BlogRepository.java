package com.petfoster.repository;

import com.petfoster.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findByAuthorId(Long authorId);
}
