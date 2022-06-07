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
                    <c:choose>
                    <c:when test="${isCustomer}">
                    <h3>Área do cliente</h3>
                    <a href="/customer"><h2>Meus dados</h2></a>
                    <a href="/order/history"><h2>Pedidos</h2></a>
                    </c:when>
                    <c:otherwise>
                    <h3>Área do parceiro</h3>
                    <a href="/merchant"><h2>Meus dados</h2></a>
                    <a href="/product/merchant/${merchant.merchantId}"><h2>Meus Produtos</h2></a>
                    <a href="/product/create"><h2>Novo produto</h2></a>
                    </c:otherwise>
                    </c:choose>
                    <a href="/logout"><h2>Logout</h2></a>
                </div>
            </aside>
            <div class="wrapper">
                <div class="title">
                    <h1>Meus dados</h1>
                </div>
                <c:choose>
                <c:when test="${isCustomer}">
                <form action="/customer/update" method="post">
                </c:when>
                <c:otherwise>
                <form action="/merchant/update" method="post">
                </c:otherwise>
                </c:choose>
                    <c:choose>
                    <c:when test="${isCustomer}">
                    <div class="field">
                        <input type="text" name="firstName" value="${customer.firstName}" required>
                        <label>Primeiro nome</label>
                    </div>
                    <div class="field">
                        <input type="text" name="lastName" value="${customer.lastName}" required>
                        <label>Último nome</label>
                    </div>
                    <div class="field">
                        <input type="text" name="cpf" value="${customer.cpf}" required>
                        <label>CPF</label>
                    </div>
                    </c:when>
                    <c:otherwise>
                    <div class="field">
                        <input type="text" name="socialName" value="${merchant.socialName}" required>
                        <label>Razão social</label>
                    </div>
                    <div class="field">
                        <input type="text" name="ownerName" value="${merchant.ownerName}" required>
                        <label>Nome do proprietário</label>
                    </div>
                    <div class="field">
                        <input type="text" name="cnpj" value="${merchant.cnpj}" required>
                        <label>CNPJ</label>
                    </div>
                    </c:otherwise>
                    </c:choose>
                    <div class="field">
                        <input type="text" name="address" value="${account.address}" required>
                        <label>Endereço</label>
                    </div>
                    <div class="field">
                        <input type="text" name="phone" value="${account.phone}" required>
                        <label>Telefone</label>
                    </div>
                    <div class="field">
                        <input type="submit" value="Salvar">
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:template>