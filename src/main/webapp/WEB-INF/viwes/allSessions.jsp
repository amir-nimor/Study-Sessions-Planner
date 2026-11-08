<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 11/8/2026
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>All sessions</title>
</head>
<body>

<c:forEach var="session" items="${allSessions}">
    <li>${session.minutes} <a href="session/${session.id}">details</a> </li>
</c:forEach>


</body>
</html>
