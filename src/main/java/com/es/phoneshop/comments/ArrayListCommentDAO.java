package com.es.phoneshop.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListCommentDAO implements CommentDao {
    private static CommentDao commentDao;
    private List<Comment> comments;

    private ArrayListCommentDAO() {
        comments = new ArrayList<>();
    }

    public static CommentDao getInstance() {
        if (commentDao == null) {
            synchronized (ArrayListCommentDAO.class) {
                if (commentDao == null) {
                    commentDao = new ArrayListCommentDAO();
                }
            }
        }
        return commentDao;
    }

    @Override
    public void saveComment(Comment comment) {
        if (comment == null) {
            throw new NullPointerException();
        } else {
            this.comments.add(comment);
        }
    }

    @Override
    public List<Comment> getCommentById(Long id) {
        return comments.stream()
                .filter(comm -> comm.getProduct_id().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
