<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="loginPageDto" type="com.store.electronic.entity.LoginPageDto"--%>
<!doctype html>
<html lang="en">
        <link rel="stylesheet" href="../../css/style.css">
        <head>
            <title>Electronic store </title>
        </head>

<body>
        <div>
            <h2 class="block">ELECTRONIC STORE</h2>
        </div>

        <div class="block2">
            <p class="block3">
                <c:if test="${sessionScope.authUser != null}">
                    <a>
                            ${sessionScope.authUser.role} : ${sessionScope.authUser.login}
                    </a>
                </c:if>
            </p>
                <p align="left">Login : ${loginPageDto.login}</p>
                <p align="left">Password : ${loginPageDto.password}</p>
                <p align="left">Name : ${loginPageDto.userName}</p>
                <p align="left">Email : ${loginPageDto.email}</p>
                <p align="left">Created time : ${loginPageDto.create_time}</p>
        </div>

        <div class="block2">
            <button onclick="location.href='/'">Back to main menu</button>
        </div>
</body>
</html>
