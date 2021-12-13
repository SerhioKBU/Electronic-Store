<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </div>
    <div class="block2">
        <form method="post">
                <h2>Registration:</h2>
                        Please enter your login
                            <br>
                                <label>  <input type="text" name="userName"/> </label>
                            <br>
                        Please enter your password
                            <br>
                                <label>  <input type="password" name="password"/> </label>
                            <br>
                        Please enter your email
                            <br>
                                <label>  <input type="text" name="email"/> </label>
                            <br>
                        Please enter your name
                            <br>
                                <label>  <input type="text" name="name"/>  </label>
                            <br>
                        <button name="Submit" value="Except" type="Submit">Except</button>
        </form>
    </div>
                <div class="block2">
                    <button onclick="location.href='/'">Back to main menu</button>
                </div>
    </body>
</html>
