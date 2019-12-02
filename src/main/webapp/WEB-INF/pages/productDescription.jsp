<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">

<jsp:useBean id="products" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <p>
        Phone's description
    </p>
    <p>
        <c:if test="${error!=null}">
            <span class="errorMess">
            <c:out value="You enter smth wrong OR not enough goods in stock"/>
            </span>
        </c:if>
    </p>
    <table class="table table-boarded">
        <thead class="thead-dark thead-boarded">
        <tr>
            <th>Image</th>
            <th>Description</th>
            <th>Stock</th>
            <th>Price</th>
        </tr>
        </thead>
        <tr>
            <td>
                <img class="product-tile"
                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${products.imageUrl}">
            </td>
            <td> ${products.description}</td>
            <td>${products.stock}</td>
            <td class="price">
                <fmt:formatNumber value="${products.price}" type="currency"
                                  currencySymbol="${products.currency.symbol}"/>
            </td>
        </tr>
    </table>
    <form method="post">
        <label class="font-weight-bold">Quantity</label><input class="form-control" type="text" name="quantity"
                                      value="${not empty param.quantity ? param.quantity : 1}">
        <button type="submit" class="btn btn-primary">Add to cart</button>
    </form>

    <c:choose>
        <c:when test="${error!=null}">
                    <span class="errorMess">
                        <c:out value="${error}"/>
                    </span>
        </c:when>
        <c:otherwise>
                    <span class="successMess">
                    <c:out value="${param.message}"/>
                    </span>
        </c:otherwise>
    </c:choose>

    <c:set var="cart" value="${sessionScope['cartList'].list}"/>
    <c:set var="list" value="${sessionScope['viewed'].list}"/>

    <c:if test="${cart!=null}">
        <c:forEach items="${cart}" var="item">
            <p>
                <c:out value="${item}"/>
                <c:out value="Quantity: ${item.quantity}"/>
                <c:out value="TotalCost: ${item.totalCost}"/>
            </p>
        </c:forEach>
    </c:if>

    <c:if test="${list!=null}">
        <table class="table-boarded">
            <tr>
                <c:forEach items="${list}" var="view">
                    <td>
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${view.imageUrl}">
                        <a href="products/${view.id}">${view.description}</a>
                        <p class="price">
                            <fmt:formatNumber value="${view.price}" type="currency"
                                              currencySymbol="${view.currency.symbol}"/>
                        </p>
                    </td>
                </c:forEach>
            </tr>
        </table>
    </c:if>
</tags:master>