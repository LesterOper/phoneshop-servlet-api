package com.es.phoneshop.web;

import com.es.phoneshop.Cart.*;
import com.es.phoneshop.comments.ArrayListCommentDAO;
import com.es.phoneshop.comments.Comment;
import com.es.phoneshop.comments.CommentDao;
import com.es.phoneshop.exception.NotEnoughStockException;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;

import static com.es.phoneshop.web.ProductListPageServlet.product;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailsServlet extends HttpServlet {
    private static final String QUANTITY = "quantity";
    private CartService cartService = HttpSessionCartService.getInstance();
    private Cart cartList = new Cart();
    private RecentlyViewedItems viewedItems;
    private CommentDao commentDao = ArrayListCommentDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comment> comments = commentDao.getCommentById(Long.parseLong(
                request.getRequestURI()
                        .substring(request.getRequestURI().lastIndexOf("/") + 1)));
        request.setAttribute("comm", comments);
        showPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String message = "";
        int quantity = 1;
        try {
            Long id = Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1));
            quantity = Integer.parseInt(request.getParameter(QUANTITY));
            ProductDao product = ArrayListProductDao.getInstance();
            if (product.getProduct(id).getStock() < quantity) {
                throw new IllegalArgumentException();
            }

            cartList = cartService.getCart(request);
            cartService.addProduct(cartList, product.getProduct(id), quantity);
            message = "Added successfully";

            viewedItems = cartService.getViewedItems(request);
            if (viewedItems.getLength() < 3 &&
                    !viewedItems.isContainsProduct(product.getProduct(id))) {
                viewedItems.addToRecentlyViewed(product.getProduct(id));
            }
        } catch (NumberFormatException e) {
            error = "Quantity is not a number";
        } catch (NotEnoughStockException e) {
            error = "Not enough stock";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            try {
                request.setAttribute(QUANTITY, getLocale(request, Integer.toString(quantity)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            showPage(request, response);
            return;
        } else {
            response.sendRedirect(request.getRequestURI() + "?message=" + message);
        }
    }

    public void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products",
                product.getProduct(Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1))));
        request.getRequestDispatcher("/WEB-INF/pages/productDescription.jsp").
                forward(request, response);
    }

    private int getLocale(HttpServletRequest request, String quantity) throws ParseException {
        Locale locale = request.getLocale();
        return NumberFormat.getIntegerInstance(locale).parse(quantity).intValue();
    }
}
