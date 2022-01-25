<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Electronic store</title>
    <style>
        .block3 {
            color: #eeba37;
        }
    </style>
</head>

<body>
    <div>
        <h1 class="block"> <i> ELECTRONICS STORE </i>
            <img src="/img/store_logo.jpg" width="100" height="100"></h1>
    </div>

    <div class="block2">

        <button onclick="location.href='/electronic-store/registration'">Registration</button>
        <button onclick="location.href='/electronic-store/login'">Login</button>
        <button onclick="location.href='/electronic-store/category'">Category</button>
        <button onclick="location.href='/electronic-store/basket'">Basket</button>
<%--        <button onclick="location.href='/electronic-store/products'">Products</button>--%>
<%--        <button onclick="location.href='/electronic-store/buyProduct'">Buy Products</button>--%>

        <p class="block3">
        <c:if test="${sessionScope.authUser != null}">
            <a>
                    ${sessionScope.authUser.role} : ${sessionScope.authUser.login}
            </a>
        </c:if>
        </p>

        <footer>
            <span> <i> <br> Address: Ukraine, Dnipro <br>
             electron_store@mail.dp </i>
            </span>
        </footer>
    </div>
</body>
</html>
