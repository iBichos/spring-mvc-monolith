<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/product.css" media="screen" />
    </jsp:attribute>
    <jsp:body>
        <div id="product">
            <section id="image-section">
                <img src="/resources/images/<c:out value="${product.productId}"></c:out>.png"
                     alt="Imagem do produto"/>
            </section>
            <section id="details-section">
                <h3 class="product-name"><c:out value="${product.name}"></c:out></h3>
                <div class="product-price">
                    <div class="monetary-symbol">R$</div>
                    <c:out value="${product.price}"></c:out>
                </div>
                <p><c:out value="${product.description}"></c:out></p>
            </section>
            <section id="payment-section">
                <div class="product-price">
                    <div class="monetary-symbol">R$</div>
                    <c:out value="${product.price}"></c:out>
                </div>
                <p>Em estoque, <c:out value="${product.amountInStock}"></c:out> itens</p>
                <p>Entrega <b>GRÁTIS: Quinta-feira, 30 de Junho</b></p>
                <div id="product-payment">
                    <button id="add-to-cart" value="<c:out value="${product.productId}"></c:out>">Adicionar ao carrinho</button>
                    <button id="buy-now" value="<c:out value="${product.productId}"></c:out>">Comprar agora</button>
                </div>
                <div>
                    <p>Enviado por <c:out value="${merchant.socialName}"></c:out></p>
                    <p>Entregue por iBichos</p>
                </div>
            </section>
        </div>
    </jsp:body>
</tags:template>