package com.es.phoneshop.web;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private CheckoutPageServlet servlet = new CheckoutPageServlet();
    private CartService cartService = HttpSessionCartService.getInstance();

    @Before
    public void setup() {
        lenient().when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test(expected = NullPointerException.class)
    public void testDoGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test(expected = NullPointerException.class)
    public void testDoPost() throws ServletException, IOException {
        request.setAttribute("name", "Alex");
        request.setAttribute("phone", "256243322");
        request.setAttribute("date", "04");
        request.setAttribute("address", "Pushkina");
        servlet.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test(expected = NullPointerException.class)
    public void testDoPostWithInvalidName() throws ServletException, IOException {
        request.setAttribute("name", "");
        request.setAttribute("phone", "256243322");
        request.setAttribute("date", "04");
        request.setAttribute("address", "Pushkina");
        servlet.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test(expected = NullPointerException.class)
    public void testDoPostWithInvalidPhone() throws ServletException, IOException {
        request.setAttribute("name", "Alex");
        request.setAttribute("phone", null);
        request.setAttribute("date", "04");
        request.setAttribute("address", "Pushkina");
        servlet.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test(expected = NullPointerException.class)
    public void testDoPostWithAllInvalidFields() throws ServletException, IOException {
        request.setAttribute("name", "");
        request.setAttribute("phone", null);
        request.setAttribute("date", "");
        request.setAttribute("address", null);
        servlet.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}
