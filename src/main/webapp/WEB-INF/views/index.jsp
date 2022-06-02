<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/catalog.css" media="screen"/>
    </jsp:attribute>
    <jsp:attribute name="scripts">
         <script src="/resources/js/showProduct.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div id="catalog">
            <c:forEach var="product" items="${products}">
                <div class="product">
                    <img src="/resources/images/<c:out value="${product.productId}"></c:out>.png"
                         alt="Imagem do produto"/>
                    <h3 class="product-name"><c:out value="${product.name}"></c:out></h3>
                    <div class="product-price">
                        <div class="monetary-symbol">R$</div>
                        <c:out value="${product.price}"></c:out>
                    </div>
                    <span id="productId" class="hidden"><c:out value="${product.productId}"></c:out></span>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</tags:template>