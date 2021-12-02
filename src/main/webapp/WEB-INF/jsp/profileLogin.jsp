<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="loginPageDto" type="com.store.electronic.entity.LoginPageDto"--%>
<html>
<head>
    <title>User Login Profile </title>
</head>
<div>
    <h1>ELECTRONIC STORE</h1>
</div>
<body>
User Profile
<%--<p>Id : ${loginPageDto.id} </p>--%>
<p>User login : ${loginPageDto.login}</p>
<p>User password : ${loginPageDto.password}</p>
<p>User name : ${loginPageDto.userName}</p>
<p>User email : ${loginPageDto.email}</p>
<%--<p>E-mail : ${account.createTime}</p>--%>

<div>
    <button onclick="location.href='/'">Back to main menu</button>
</div>
</body>
</html>
