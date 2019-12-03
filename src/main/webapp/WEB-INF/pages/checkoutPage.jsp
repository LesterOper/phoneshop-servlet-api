<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">

<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <p>
        It is your cart!
    </p>
    <c:set var="order" value="${requestScope['order'].list}"/>
    <table class="table table-boarded">
        <thead class="thead-dark">
        <tr>
            <th>Image</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${order}" var="orderItem" varStatus="status">
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
    <table class="table table-boarded">
        <thead class="thead-dark">
        <tr>
            <th>Subtotal</th>
            <th>Delivery cost</th>
            <th>Total quantity</th>
            <th>Total cost</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                    ${requestScope["order"].subtotal}
            </td>
            <td>
                    ${requestScope["order"].deliveryCost}
            </td>
            <td>
                    ${requestScope["order"].totalCartQuantity}
            </td>
            <td>
                    ${requestScope["order"].totalCartCost}
            </td>
        </tr>
        </tbody>
    </table>
    <form method="post">
        <tags:Input label="Name" name="name" errorMap="${requestScope['error']}"/>
        <tags:Input label="Phone" name="phone" errorMap="${requestScope['error']}"/>
        <tags:Input label="Date" name="date" errorMap="${requestScope['error']}"/>
        <tags:Input label="Address" name="address" errorMap="${requestScope['error']}"/>
        <select name="deliver" size="1">
            <option selected>Money</option>
            <option>Cart</option>
            <option>PickUp</option>
        </select>
        <button>
            ADD
        </button>
    </form>
</tags:master>