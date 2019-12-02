<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">

<tags:master pageTitle="Order overview">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <c:set var="order" value="${requestScope['order'].list}"/>
    <table class="table table-boarded table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Image</th>
            <th>Description</th>
            <th>Stock</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${order}" var="orderItem">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${orderItem.product.imageUrl}">
                </td>
                <td>
                        ${orderItem.product.description}
                </td>
                <td>
                        ${orderItem.quantity}
                </td>
                <td>
                        ${orderItem.totalCost}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-boarded table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Total quantity</th>
            <th>Subtotal</th>
            <th>Delivery cost</th>
            <th>Total cost</th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td>
                    ${requestScope["order"].totalCartQuantity}
            </td>
            <td>
                    ${requestScope["order"].subtotal}

            </td>
            <td>
                    ${requestScope["order"].deliveryCost}
            </td>
            <td>
                    ${requestScope["order"].totalCartCost}
            </td>
        </tr>
        </tbody>
    </table>

    <table class="table table-boarded table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Phone</th>
            <th>Date</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                    ${requestScope["order"].name}
            </td>
            <td>
                    ${requestScope["order"].phone}
            </td>
            <td>
                    ${requestScope["order"].date}
            </td>
            <td>
                    ${requestScope["order"].address}
            </td>
        </tr>
        </tbody>
    </table>

</tags:master>