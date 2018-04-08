package com.codextask.backend.service;

import com.codextask.backend.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> getComments();

    public void addComment(Comment comment);

    public void deleteComment(Long id);

    public Comment getCommentById(Long id);
}
