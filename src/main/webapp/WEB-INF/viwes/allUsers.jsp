<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 11/8/2026
  Time: 11:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>all users</title>
</head>
<body>


<c:forEach var="user" items="${users}">
    <li>${user.username}</li>
</c:forEach>


</body>
</html>
