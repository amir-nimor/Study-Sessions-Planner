<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 11/8/2026
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>session form</title>
</head>
<body>


<form method="POST" action="add">

    <label>minutes
        <input type="number" name="minutes">
    </label>

    <label>notes
    <input type="text" name="notes">
    </label>


    <label>
        food:
        <select name="subjectId">
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.id}"  >${subject.name}</option>
            </c:forEach>
        </select>
    </label>

    <button type="submit">Save</button>

</form>


</body>
</html>
