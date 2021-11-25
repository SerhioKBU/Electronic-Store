<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.store.electronic.entity.User"--%>
<html>
<head>
    <title>User Profile </title>
</head>
<body>
User Profile
<p>Id : ${user.id} </p>
<p>Name : ${user.userName}</p>
<p>Password : ${user.password}</p>
<p>E-mail : ${user.email}</p>
</body>
</html>
