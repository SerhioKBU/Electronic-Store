<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" href="../../css/style.css">
        <head>
            <title>Electronic store</title>
        </head>
<body>
        <div>
            <h1 class="block"> <i> ELECTRONICS STORE </i>
                <img src="/img/store_logo.jpg" width="100" height="100"></h1>
        </div>
        <div class="block2">
            <form method="post">
                Please enter your login
                    <br>
                        <label>
                            <input type="text" name="login"/>
                        </label>
                    <br>
                Please enter your password
                    <br>
                        <label>
                            <input type="password" name="password"/>
                        </label>
                    <br>
                    <button name="Submit" value="Login" type="Submit">Login</button>
            </form>
        </div>

        <div class="block2">
                <button onclick="location.href='/'">Back to main menu</button>
        </div>
</body>
</html>