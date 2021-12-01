<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="account" type="com.store.electronic.entity.Account"--%>
<html>
<head>
    <title>Registration Account Profile </title>
</head>
<body>
User Account Profile
<p>Id : ${account.id} </p>
<p>Login : ${account.login}</p>
<p>Password : ${account.password}</p>

<div>
    <button onclick="location.href='/'">Back to main menu</button>
</div>

</body>
</html>
