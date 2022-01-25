<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="category" type="com.store.electronic.entity.Category"--%>
<%--@elvariable id="basket" type="com.store.electronic.entity.Basket"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<c:forEach items="${basket.categories}" var="category">
    <br>
    <c:out value="${category.id}"/>
    <c:out value="${category.name}"/>
    <br>
</c:forEach>

<a href="category">view all category</a>

</body>
</html>
