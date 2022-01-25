<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.electronic.entity.Category" %>
<%--@elvariable id="category" type="com.store.electronic.entity.Category"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../../css/style.css">
    <head>
        <title>Electronic store</title>
        <style>
            table{
                border-collapse:separate;
                border-spacing:5px;
                width:80%;
                height:20%;
                margin:0 auto;
                font:16px/1.25 Tahoma
            }
            tr{
                height:5%
            }
            td{
                width:32%;
                background: #f6c526;
                text-align:left;
            }
            td:first-child{
                width:5%;
                font-weight:bold
            }
            a{
                text-decoration: none;
                color:#000
            }
        </style>
    </head>

<body>
    <div>
        <h1 class="block"> <i> ELECTRONICS STORE </i>
            <img src="/img/store_logo.jpg" width="100" height="100">
        </h1>
    </div>

    <form method="post">
        <div class="block2">
            <h2> Categories group:</h2>
            <p class="block3">
                <c:if test="${sessionScope.authUser != null}">
                    <a>
                            ${sessionScope.authUser.role} : ${sessionScope.authUser.login}
                    </a>
                </c:if>
            </p>

            <c:forEach var="category" items="${sessionScope.categoryList}">
            <table>
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <p> </p>
                </tr>
                <a href="addToBasket?categoryId=${category.id}">Buy this category</a>
            </table>
            </c:forEach>

            <a href="basket">view my basket</a>
    </form>


<%--            <c:forEach items="${category}" var="category">--%>
<%--                <br>--%>
<%--                <c:out value="${category.id}"/>--%>
<%--                <c:out value="${category.name}"/>--%>
<%--                <a href="addToBasket?carId=${category.id}">Buy these category</a>--%>
<%--            </c:forEach>--%>

            <div>
                <%
                    java.util.Date now = new java.util.Date();
                    String time = "Current time: " + now;
                %>
                <%= time %>
            </div>
                <div>
                    <button onclick="location.href='/'">Back to main menu</button>
                </div>
        </div>
</body>
</html>


<%--        <%--%>
<%--            List<Category> serviceList = (List<Category>) request.getAttribute("serviceList");--%>

<%--            if (serviceList != null && !serviceList.isEmpty()) {--%>
<%--                out.println("<ui>");--%>
<%--                for (Category s : serviceList) {--%>
<%--                    out.println("<li>" + s + "</li>");--%>
<%--                }--%>
<%--                out.println("</ui>");--%>
<%--            } else out.println("<p>There are no users yet!</p>");--%>
<%--        %>--%>