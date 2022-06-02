<%@ tag description="Template" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ attribute name="style" fragment="true" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="scripts" fragment="true" %>
<jsp:useBean id="userInfo" scope="session" class="ibichos.foundation.monolith.service.AccountService" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="/resources/icons/favicon.png">

        <!-- Font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100;200;300;400;600;700&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" type="text/css" href="/resources/css/common.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="/resources/css/top-bar.css" media="screen" />

        <!-- Customized CSS -->
        <jsp:invoke fragment="style"/>

        <!-- Customized title -->
        <jsp:invoke fragment="title"/>
    </head>
    <body>
        <nav id="top-bar">
            <div class="group">
                <a href="/" class="item title">
                    <h1 class="text">iBichos</h1>
                </a>
            </div>
            <div class="group">
                <a href="/category/rations" class="item"><h2 class="text">Rações</h2></a>
                <a href="/category/collars" class="item"><h2 class="text">Coleiras</h2></a>
                <a href="/category/toys" class="item"><h2 class="text">Brinquedos</h2></a>
            </div>
            <div class="group">
                <a class="item" id="cart" href="/cart/">
                    <svg version="1.1" id="cart-icon" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                         width="40px" height="36px" viewBox="0 0 40 36" style="enable-background:new 0 0 40 36;" xml:space="preserve"><g id="page" sketch:type="MSPage">
                            <g id="canvas" transform="translate(-84.000000, -410.000000)" sketch:type="MSArtboardGroup">
                                <path id="cart-properties" sketch:type="MSShapeGroup" class="st0" d="M94.5,434.6h24.8l4.7-15.7H92.2l-1.3-8.9H84v4.8h3.1l3.7,27.8h0.1
                                    c0,1.9,1.8,3.4,3.9,3.4c2.2,0,3.9-1.5,3.9-3.4h12.8c0,1.9,1.8,3.4,3.9,3.4c2.2,0,3.9-1.5,3.9-3.4h1.7v-3.9l-25.8-0.1L94.5,434.6"></path>
                            </g>
                        </g>
                    </svg>
                    <span id="cart-counter">0</span>
                </a>
            <c:choose>
                <c:when test="${isLoggedIn}">
                    <c:choose>
                        <c:when test="${isCustomer}">
                            <a href="/customer/" class="item"><h2 class="text">Perfil</h2></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/merchant/" class="item"><h2 class="text">Perfil</h2></a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <a href="/login/customer" class="item"><h2 class="text">Entrar</h2></a>
                    <a href="/sign-up/customer" class="item"><h2 class="text">Cadastre-se</h2></a>
                </c:otherwise>
            </c:choose>
            </div>
        </nav>
        <jsp:invoke fragment="header"/>

        <main>
            <jsp:doBody/>
        </main>

        <!-- JQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

        <!-- Cart update -->
        <script src="/resources/js/cartUpdate.js"></script>

        <!-- Customized scripts -->
        <jsp:invoke fragment="scripts"/>
    </body>
</html>