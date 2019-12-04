<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <form>
        <input class="form-control" aria-describedby="basic-addon1" name="query" value= ${not empty param.query ? param.query : ""}>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Image</th>
            <th scope="col">
                Description
                <a href="products?sort=description&Board=asc&query =${param.query}">asc</a>
                <a href="products?sort=description&Board=desc&query =${param.query}">desc</a>
            </th>
            <th scope="col">
                Price
                <a href="products?sort=price&Board=asc&query =${param.query}">asc</a>
                <a href="products?sort=price&Board=desc&query =${param.query}">desc</a>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                </td>
                <td>
                    <a href="products/${product.id}">${product.description}</a></td>
                <td class="price">
                    <a
                            href=""
                            onclick="window.open('products/popup?product=${product.id}', '_blank', 'width = 400,height = 400')">
                        <fmt:formatNumber value="${product.price}" type="currency"
                                          currencySymbol="${product.currency.symbol}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:master>