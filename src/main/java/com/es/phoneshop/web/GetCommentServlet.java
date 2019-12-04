
package com.es.phoneshop.web;

import com.es.phoneshop.comments.ArrayListCommentDAO;
import com.es.phoneshop.comments.Comment;
import com.es.phoneshop.comments.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.web.ProductListPageServlet.product;

public class GetCommentServlet extends HttpServlet {
    private CommentDao commentDao = ArrayListCommentDAO.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int rate = Integer.parseInt(request.getParameter("rate"));
        String comment = request.getParameter("comment");
        Long id = Long.parseLong(request.getParameter("product_id"));

        Comment comment1 = new Comment();
        comment1.setName(name);
        comment1.setRate(rate);
        comment1.setComment(comment);
        comment1.setProduct_id(id);
        commentDao.saveComment(comment1);
        response.sendRedirect(request.getContextPath()+"/products/" + id);
    }
}
