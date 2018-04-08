package com.codextask.backend.service.impl;


import com.codextask.backend.entity.Comment;
import com.codextask.backend.repository.CommentRepository;
import com.codextask.backend.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService{
    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    @Transactional
    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.delete(id);
    }

    @Transactional
    public Comment getCommentById(Long id){
        return commentRepository.findOne(id);
    }
}
