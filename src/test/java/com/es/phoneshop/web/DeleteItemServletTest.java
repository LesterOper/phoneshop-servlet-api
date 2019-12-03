package com.es.phoneshop.web;

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
public class DeleteItemServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private DeleteItemFromCartServlet servlet = new DeleteItemFromCartServlet();

    @Before
    public void setup() {
        lenient().when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test(expected = NumberFormatException.class)
    public void testDoPost() throws ServletException, IOException {
        servlet.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}
