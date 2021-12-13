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
        User Profile
                <p>User login : ${loginPageDto.login}</p>
                <p>User password : ${loginPageDto.password}</p>
                <p>User name : ${loginPageDto.userName}</p>
                <p>User email : ${loginPageDto.email}</p>
                <p>Created time : ${loginPageDto.create_time}</p>
        </div>

        <div class="block2">
            <button onclick="location.href='/'">Back to main menu</button>
        </div>
</body>
</html>
