<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product List">
  <p>
    Welcome to Expert-Soft training!
  </p>
  <p>
    Phone's description
  </p>
  <table>
    <thead>
      <tr>
        <td>Image</td>
        <td>Description</td>
        <td>Stock</td>
        <td>Price</td>
      </tr>
    </thead>
      <tr>
        <td>
          <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${products.imageUrl}">
        </td>
        <td> ${products.description}</td>
        <td>${products.stock}</td>
        <td class="price">
          <fmt:formatNumber value="${products.price}" type="currency" currencySymbol="${products.currency.symbol}"/>
        </td>
      </tr>
  </table>
</tags:master>