<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="account" type="com.store.electronic.entity.Account"--%>
<html>
<head>
    <title>ELECTRONIC STORE </title>
</head>
<body>

<h2>ELECTRONIC STORE</h2>

<%--User Account Profile--%>
<%--<p>Id : ${account.id} </p>--%>
<%--<p>Login : ${account.login}</p>--%>
<%--<p>Password : ${account.password}</p>--%>

<div>
    <h3>
        New User Successfully Registered: ${sessionScope.authUser.login}
    </h3>
</div>

    <%
    java.util.Date now = new java.util.Date();
    String time = "Current time: " + now;
    %>
    <%= time %>


<div>
    <button onclick="location.href='/'">Back to main menu</button>
</div>

</body>
</html>
