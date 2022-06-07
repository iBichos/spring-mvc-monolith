<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Pedidos</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/order-list.css" media="screen"/>
    </jsp:attribute>
    <jsp:body>
        <div class="orders">
            <h1>Meus produtos</h1>
            <h3 class="order-title">Data e ID do pedido:</h3>
            <c:forEach var="order" items="${orders}">
            <div class="order-details">
                <div class="order-date">${order.checkoutHour}</div>
                <div class="order-id">${order.orderId}</div>
            </div>
            </c:forEach>
        </div>
    </jsp:body>
</tags:template>