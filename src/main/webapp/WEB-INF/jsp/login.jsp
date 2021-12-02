<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ELECTRONIC STORE</title>
</head>
<body>
<div>
    <h1>ELECTRONIC STORE</h1>
</div>
<form method="post">
    Please enter your login
    <br>
    <input type="text" name="login"/>
    <br>
    Please enter your password
    <br>
    <input type="password" name="password"/>
    <br>
    <button name="Submit" value="Login" type="Submit">Login</button>
</form>

<div>
    <button onclick="location.href='/'">Back to main menu</button>
</div>
</body>
</html>