package com.unosquare.blog.springblogrestapi.service;

import com.unosquare.blog.springblogrestapi.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
