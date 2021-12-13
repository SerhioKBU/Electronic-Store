<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.electronic.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../../css/style.css">
    <head>
        <title>Electronic store</title>
    </head>

<body>
    <div>
        <h2 class="block">ELECTRONIC STORE</h2>
        <h2 class="block2"> Categories group:</h2>
    </div>

        <div class="block2">
            <c:forEach var="category" items="${sessionScope.categoryList}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <p> </p>
            </tr>
            </c:forEach>
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