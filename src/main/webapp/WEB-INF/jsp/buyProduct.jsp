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
<a href="basket">view my basket</a>
<c:forEach var="user" items="${user.userName}">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <c:out value="${product.cost}"/>
    <c:out value="${product.category}"/>
    <a href="addToBasket?carId=${car.id}">Buy these car</a>
    <br>
</c:forEach>

</body>
</html>
