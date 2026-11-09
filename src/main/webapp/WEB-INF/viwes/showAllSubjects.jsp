<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 11/8/2026
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>show all subjects</title>
</head>
<body>


<c:forEach var="subject" items="${subjects}">
    <li>${subject.name} <a href="${subject.id}">deatails</a> </li>
</c:forEach>


</body>
</html>
