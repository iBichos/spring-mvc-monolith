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
            <c:when test="${!tuples.isEmpty()}">
                <div id="cart-dashboard">
                    <div id="cart-list">
                        <div class="product">
                            <div class="product-image"></div>
                            <div class="product-name"></div>
                            <div class="product-price"><p>Preço</p></div>
                            <div class="amount"><p>Quantidade</p></div>
                        </div>
                        <c:forEach var="tuple" items="${tuples}">
                            <div class="product">
                                <img class="product-image" src="/resources/images/<c:out value="${tuple.key.productId}"></c:out>.png"
                                     alt="Imagem do produto"/>
                                <div class="product-name">
                                    <h3><c:out value="${tuple.key.name}"></c:out></h3>
                                </div>
                                <div class="product-price">
                                    <p><c:out value="${tuple.key.price}"></c:out></p>
                                </div>
                                <div class="amount">
                                    <button class="increment-to-cart" value="<c:out value="${tuple.key.productId}"></c:out>">▲</button>
                                    <p id="<c:out value="${tuple.key.productId}"></c:out>"><c:out value="${tuple.value}"></c:out></p>
                                    <button class="decrement-to-cart" value="<c:out value="${tuple.key.productId}"></c:out>">▼</button>
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

                        <div id="payment-actions">
                            <button id="buy-now" value="<c:out value="${tuple.key.productId}"></c:out>">Finalizar</button>
                        </div>
                    </aside>
                </div>
            </c:when>
            <c:otherwise>
                <h1>Você ainda não adicionou itens ao carrinho :(</h1>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</tags:template>