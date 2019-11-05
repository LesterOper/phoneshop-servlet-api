<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product List">
<html>
    <head>
        <title>Price History</title>
    </head>
    <table>
    <thead>
      <tr>
        <td>Image</td>
        <td>Description</td>
        <td>Price</td>
      </tr>
    </thead>
      <tr>
        <td>
          <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${products.imageUrl}">
        </td>
        <td> ${products.description}</td>
        <td class="price">01.11.2019
          <fmt:formatNumber value="${products.price}" type="currency" currencySymbol="${products.currency.symbol}"/>
        </td>
      </tr>
      <tr>
          <td></td>
          <td></td>
          <td class = "price">10.11.2019
              <fmt:formatNumber value="${products.price/2}" type="currency" currencySymbol="${products.currency.symbol}"/>
          </td>
      </tr>
      <tr>
          <td></td>
          <td></td>
          <td class = "price">30.11.2019
             <fmt:formatNumber value="${products.price/4}" type="currency" currencySymbol="${products.currency.symbol}"/>
          </td>
      </tr>
  </table>
</html>
</tags:master>