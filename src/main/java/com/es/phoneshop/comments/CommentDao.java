package com.es.phoneshop.comments;

import java.util.List;

public interface CommentDao {
    void saveComment(Comment comment);
    List<Comment> getCommentById(Long id);
}
