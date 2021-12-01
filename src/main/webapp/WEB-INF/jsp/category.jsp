<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.electronic.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ELECTRONIC STORE</title>
</head>

<body>
<div>
    <h1>ELECTRONIC STORE</h1>
</div>

<div>
    <div>
        <div>
            <h2>Categories group</h2>
        </div>
    <c:forEach var="service" items="${sessionScope.serviceList}">

        <tr>
            <td>
                    ${service.id}
            </td>
            <td>
                    ${service.name}
            </td>
            <p> </p>
        </tr>

    </c:forEach>
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
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main menu</button>
</div>
</body>
</html>
