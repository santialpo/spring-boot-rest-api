package com.unosquare.blog.springblogrestapi.repository;

import com.unosquare.blog.springblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
