<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Sign-up</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="resources/css/form.css" media="screen" />
    </jsp:attribute>
    <jsp:body>
        <div class="wrapper">
            <div class="title">
                Crie sua conta
            </div>
            <form action="/sign-up" method="post">
                <div class="form-row">
                    <div class="field">
                        <input type="text" name="name" required>
                        <label>Nome</label>
                    </div>
                    <div class="field">
                        <input type="text" name="cpf" required>
                        <label>CPF</label>
                    </div>
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
                <div class="signup-link">
                    Já possui uma conta? <a href="/login">Entrar</a>
                </div>
            </form>
        </div>
    </jsp:body>
</tags:template>