<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="account" type="com.store.electronic.entity.Account"--%>
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
        <h3>
            New User Successfully Registered: ${sessionScope.authUser.login}
        </h3>
        <%
            java.util.Date now = new java.util.Date();
            String time = "Current time: " + now;
        %>
        <%= time %>
    </div>
    <div class="block2">
        <button onclick="location.href='/'">Back to main menu</button>
    </div>
</body>
</html>
