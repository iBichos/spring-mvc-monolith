<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<tags:template>
    <jsp:attribute name="title">
        <title>iBichos · Login</title>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" type="text/css" href="resources/css/form.css" media="screen" />
    </jsp:attribute>
    <jsp:body>
        <div class="wrapper">
            <div class="title">
                <h1>Acesse sua conta</h1>
            </div>
            <form action="/merchant-login" method="post">
                <div class="field">
                    <input type="text" name="email" required>
                    <label>E-mail</label>
                </div>
                <div class="field">
                    <input type="password" name="password" required>
                    <label>Senha</label>
                </div>
                <div class="content">
                    <div class="checkbox">
                        <input type="checkbox" id="remember-me">
                        <label for="remember-me">Lembrar minha conta</label>
                    </div>
                    <div class="pass-link">
                        <a href="#">Esqueceu sua senha?</a>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${loginFailure}">
                        <div class="content">
                            E-mail e/ou senha incorretos. Tente novamente!
                        </div>
                    </c:when>
                </c:choose>
                <div class="field">
                    <input type="submit" value="Entrar">
                </div>
                <div class="signup-link">
                    Não possui uma conta? <a href="/sign-up">Cadastre-se</a>
                </div>
            </form>
        </div>
    </jsp:body>
</tags:template>