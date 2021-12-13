<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Electronic store</title>
    <style>
        .block3 {
            color: #a10606;
        }
    </style>
</head>

<body>
    <div>
        <h2 class="block">ELECTRONIC STORE</h2>
    </div>

    <div class="block2">    <!-- buttons holder -->
        <button onclick="location.href='/electronic-store/registration'">Registration</button>
        <button onclick="location.href='/electronic-store/login'">Login</button>
        <button onclick="location.href='/electronic-store/category'">Category</button>
        <button onclick="location.href='/electronic-store/buyProduct'">Products</button>

        <p class="block3">
        <c:if test="${sessionScope.authUser != null}">
            <a>
                    ${sessionScope.authUser.role} : ${sessionScope.authUser.login}
            </a>
        </c:if>
        </p>
    </div>
</body>
</html>
