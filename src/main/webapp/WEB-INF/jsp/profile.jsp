<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="account" type="com.store.electronic.entity.Account"--%>
<html>
<head>
    <title>User Profile </title>
</head>
<body>
User Profile
<p>Id : ${account.id} </p>
<p>Login : ${account.login}</p>
<p>Password : ${account.password}</p>
<p>E-mail : ${account.createTime}</p>
</body>
<a href="\login.jsp">Login page</a>
</html>
