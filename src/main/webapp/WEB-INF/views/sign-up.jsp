<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Sign-up</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="/resources/css/form.css" media="screen" />
    </jsp:attribute>
    <jsp:body>
        <div class="wrapper">
            <div class="title">
                Crie sua conta
                <c:choose>
                    <c:when test="${isMerchant}">
                        <h2 id="lead">Parceiros</h2>
                    </c:when>
                </c:choose>
            </div>
            <c:choose>
                <c:when test="${isCustomer}">
                <form action="/sign-up/customer" method="post">
                </c:when>
                <c:otherwise>
                <form action="/sign-up/merchant" method="post">
                </c:otherwise>
            </c:choose>
                <div class="form-row">
                    <c:choose>
                    <c:when test="${isCustomer}">
                        <div class="field">
                            <input type="text" name="firstName" required>
                            <label>Primeiro nome</label>
                        </div>
                        <div class="field">
                            <input type="text" name="lastName" required>
                            <label>Último nome</label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="field">
                            <input type="text" name="socialName" required>
                            <label>Razão social</label>
                        </div>
                        <div class="field">
                            <input type="text" name="ownerName" required>
                            <label>Nome do proprietário</label>
                        </div>
                    </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-row">
                    <c:choose>
                        <c:when test="${isCustomer}">
                            <div class="field">
                                <input type="text" name="cpf" required>
                                <label>CPF</label>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="field">
                                <input type="text" name="cnpj" required>
                                <label>CNPJ</label>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-row">
                    <div class="field">
                        <input type="text" name="address" required>
                        <label>Endereço</label>
                    </div>
                    <div class="field">
                        <input type="text" name="phone" required>
                        <label>Telefone</label>
                    </div>
                </div>
                <div class="form-row">
                    <div class="field">
                        <input type="email" name="email" required>
                        <label>E-mail</label>
                    </div>
                    <div class="field">
                        <input type="email" name="emailRepeat" required>
                        <label>Repita seu e-mail</label>
                    </div>
                </div>
                <div class="form-row">
                    <div class="field">
                        <input type="password" name="passwordRepeat" required>
                        <label>Senha</label>
                    </div>
                    <div class="field">
                        <input type="password" name="password" required>
                        <label>Repita sua senha</label>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${emailUnmatched}">
                        <div class="content">
                            Os e-mails estão diferentes.
                        </div>
                    </c:when>
                    <c:when test="${passwordUnmatched}">
                        <div class="content">
                            As senhas estão diferentes.
                        </div>
                    </c:when>
                </c:choose>
                <div class="field">
                    <input type="submit" value="Cadastrar">
                </div>
                <c:choose>
                    <c:when test="${isCustomer}">
                        <div class="form-link">
                            Já possui uma conta? <a href="/login/customer">Entrar</a>
                        </div>
                        <div class="form-link">
                            Quer vender coisas no iBichos? <a href="/sign-up/merchant">Cadastre sua loja</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-link">
                            Já possui uma cadastro? <a href="/login/merchant">Entrar</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </jsp:body>
</tags:template>