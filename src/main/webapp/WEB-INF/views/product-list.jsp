<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Perfil</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/form.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/product-list.css" media="screen"/>
    </jsp:attribute>
    <jsp:body>
        <div class="products">
            <h1>Meus produtos</h1>
            <c:forEach var="product" items="${products}">
            <div class="product">
                <a class="product-details" href="/product/${product.productId}">
                    <img class="product-image" src="/resources/images/${product.productId}.png">
                    <div class="product-name">
                        <h2>${product.name}</h2>
                    </div>
                    <div class="product-amount-in-stock">
                        <h2>${product.amountInStock} unidade(s)</h2>
                    </div>
                </a>
                <div class="product-actions">
                    <a href="/product/${product.productId}/edit">Editar</a>
                    <a href="/product/${product.productId}/delete">Remover</a>
                </div>
            </div>
            </c:forEach>
        </div>
    </jsp:body>
</tags:template>