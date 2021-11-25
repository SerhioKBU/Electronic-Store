<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="product" type="com.store.electronic.entity.Product"--%>
<%--@elvariable id="basket" type="com.store.electronic.entity.Basket"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<c:forEach items="${basket.products}" var="product">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <br>
</c:forEach>

<a href="products">view all products</a>

</body>
</html>
