package com.unosquare.blog.springblogrestapi.service;

import com.unosquare.blog.springblogrestapi.dto.PostDto;
import com.unosquare.blog.springblogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
