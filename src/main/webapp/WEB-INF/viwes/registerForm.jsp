<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 11/7/2026
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register form</title>
</head>
<body>


<form action="/register" method="post">

    <label>Username:
    <input type="text" name="username" /></label>
    </label>


    <label>Password:
    <input type="password" name="password" /></label>
    </label>

    <label>Confirm password:
    <input type="password" name="confirm" /></label>
    </label>

    <label>fullname:
    <input type="text" name="fullname" /></label>
    </label>

    <button type="submit">Submit</button>


</form>

</body>
</html>
