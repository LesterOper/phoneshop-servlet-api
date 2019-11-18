<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <p>
        It is your cart!
    </p>
    <c:if test="${errors==null}">
        <span class="successMess">
            <c:out value="${param.message}"/>
        </span>
    </c:if>
    <c:set var="cart" value="${sessionScope['cartList'].list}"/>
    <c:if test="${empty cart}">
        <span class="errorMess">
            <c:out value="Your cart is empty. Please, add smth to cart!"/>
        </span>
    </c:if>
    <c:if test="${not empty cart}">
        <table>
            <thead>
            <tr>
                <td>Image</td>
                <td>Description</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
            </thead>
            <c:set var="error" value="${requestScope['errors']}"/>
            <form id="deleteCartItem" action="${pageContext.servletContext.contextPath}/products/cart/deleteItem"
                  method="post">
                <c:forEach items="${cart}" var="cartItem" varStatus="status">
                <tr>
                    <td>
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                    </td>
                    <td>
                            ${cartItem.product.description}
                    </td>
                    <td>
                        <input type="text" name="quantity" value="${not empty paramValues.quantity[status.index]
                                                        ? paramValues.quantity[status.index]: cartItem.quantity}">
                        <input type="hidden" name="productId" value="${cartItem.product.id}">
                        <c:if test="${error[cartItem.product]!=null}">
                            <span class="errorMess">
                                <c:out value="${error[cartItem.product]}"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                            ${cartItem.totalCost}
                    </td>
                    <td>
                        <button form="deleteCartItem" name="productId" value="${cartItem.product.id}">
                            DELETE
                        </button>
                    </td>
                </tr>
                </c:forEach>
        </table>
        <button formaction="${pageContext.servletContext.contextPath}/products/cart">UPDATE</button>
        </form>
    </c:if>
</tags:master>