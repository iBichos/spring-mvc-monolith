<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Carrinho</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/cart.css" media="screen"/>
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${!productAmounts.isEmpty()}">
                <div id="cart-dashboard">
                    <div id="cart-list">
                        <div class="product">
                            <div class="product-image"></div>
                            <div class="product-name"></div>
                            <div class="product-price"><p>Preço</p></div>
                            <div class="amount"><p>Quantidade</p></div>
                        </div>
                        <c:forEach var="productAmount" items="${productAmounts}">
                            <div class="product">
                                <img class="product-image" src="/resources/images/${productAmount.product.productId}.png"
                                     alt="Imagem do produto"/>
                                <div class="product-name">
                                    <h3>${productAmount.product.name}</h3>
                                </div>
                                <div class="product-price">
                                    <p>${productAmount.product.price}</p>
                                </div>
                                <div class="amount">
                                    <button class="increment-to-cart" value="${productAmount.product.productId}">▲</button>
                                    <p id="${productAmount.product.productId}">${productAmount.amount}</p>
                                    <button class="decrement-to-cart" value="${productAmount.product.productId}">▼</button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <aside id="payment">
                        <h3>Finalize sua compra</h3>
                        <div id="payment-tongue">
                            <div>Total: R$</div>
                            <div id="cart-total-price">
                                0
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${!isMerchant}">
                                <a id="payment-actions" href="/order/create">
                                    <button id="buy-now">Finalizar</button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <br>
                                <p>Você precisa ser um cliente para prosseguir :(</p>
                            </c:otherwise>
                        </c:choose>
                    </aside>
                </div>
            </c:when>
            <c:otherwise>
                <h1>Você ainda não adicionou itens ao carrinho :(</h1>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</tags:template>