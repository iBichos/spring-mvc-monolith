<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Perfil</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/form.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/account.css" media="screen"/>
    </jsp:attribute>
    <jsp:body>
        <div id="account">
            <aside class="menu">
                <div>
                    <h3>Área do parceiro</h3>
                    <a href="/merchant"><h2>Meus dados</h2></a>
                    <a href="/merchant/products"><h2>Meus Produtos</h2></a>
                    <a href="/logout"><h2>Logout</h2></a>
                </div>
            </aside>
            <div class="wrapper">
                <div class="title">
                    <h1>Meus produtos</h1>
                </div>
                    <c:choose>
                    <c:when test="${create}">
                    <form action="/product/create" method="post" enctype="multipart/form-data">
                    </c:when>
                    <c:otherwise>
                    <form action="/product/${product.productId}/edit" method="post" enctype="multipart/form-data">
                    </c:otherwise>
                    </c:choose>
                    <div class="field">
                        <input type="text" name="name" value="${product.name}" required>
                        <label>Nome do produto</label>
                    </div>
                    <div class="field">
                        <input type="text" name="description" value="${product.description}" required>
                        <label>Descrição</label>
                    </div>
                    <div class="field">
                        <input type="text" name="price" value="${product.price}" required>
                        <label>Preço</label>
                    </div>
                    <div class="field">
                        <input type="number" name="amountInStock" value="${product.amountInStock}" required>
                        <label>Em estoque</label>
                    </div>
                    <div class="field upload">
                        <p>Selecione uma imagem para o produto</p>
                        <input type="file"
                               id="image" name="image"
                               accept="image/png">
                    </div>
                    <c:choose>
                    <c:when test="${create}">
                    <div class="field">
                        <input type="submit" value="Criar">
                    </div>
                    </c:when>
                    <c:otherwise>
                    <div class="field">
                        <input type="submit" value="Salvar">
                    </div>
                    </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:template>