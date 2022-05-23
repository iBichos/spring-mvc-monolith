<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="resources/css/catalog.css" media="screen" />
    </jsp:attribute>
    <jsp:body>
        <div id="card-container">
            <c:forEach var="product" items="${products}">
                <figure class="card">
                    <img src="resources/images/<c:out value="${product.id}"></c:out>.png" alt="Imagem do produto" />
                    <figcaption>
                        <h3><c:out value="${product.name}"></c:out></h3>
                        <p><c:out value="${product.description}"></c:out></p>
                        <div class="price">
                            R$<c:out value="${product.price}"></c:out>
                        </div>
                    </figcaption><i class="ion-android-cart"></i>
                    <a href="/add-to-cart/<c:out value="${product.id}"></c:out>"></a>
                </figure>
            </c:forEach>
        </div>
    </jsp:body>
</tags:template>