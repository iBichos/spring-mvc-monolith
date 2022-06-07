<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/order.css" media="screen"/>
    </jsp:attribute>
    <jsp:body>
        <div class="order">
            <div class="order-information">
                <div class="header">
                    <img src="/resources/icons/success.png" alt="image" class="success">
                    <h1> Sua compra foi aprovada! </h1>
                </div>
                <div class="order-details">
                    <h2> O código do seu pedido é </h2>
                    <div class="order-code">
                        ${order.orderId}
                    </div>
                </div>
                <div class="shipping-information">
                    Seu pedido será entregue logo em breve. A iBichos agradece sua compra e deseja a você e seu pet um excelente dia!
                </div>
            </div>
        </div>
    </jsp:body>
</tags:template>
