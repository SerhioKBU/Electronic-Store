<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="user" type="com.store.electronic.entity.Product"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<a href="basket">view my basket</a>

<c:forEach items="${products}" var="product">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <a href="addToBasket?productId=${product.id}">Buy these product</a>
</c:forEach>


</body>
</html>
