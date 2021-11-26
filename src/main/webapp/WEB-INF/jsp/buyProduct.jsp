<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="product" type="com.store.electronic.entity.Product"--%>
<%--@elvariable id="user" type="com.store.electronic.entity.User"--%>
<html>
<head>
    <title>Profile of purchases </title>
</head>
<body>
<c:forEach items="${user.userName}" var="user">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <c:out value="${product.cost}"/>
    <c:out value="${product.category}"/>
    <br>
</c:forEach>

</body>
</html>
