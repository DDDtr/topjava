<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Meals</h1>

<table border="1">
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <c:forEach var="mealTo" items="${mealList}">
        <javatime:format value="${mealTo.dateTime}" style="MS" var="date_time"/>
        <c:if test="${mealTo.excess}">
            <tr>
                <td><font color="red">${date_time}</font></td>
                <td><font color="red">${mealTo.description}</font></td>
                <td><font color="red">${mealTo.calories}</font></td>
                <td><font color="red">${mealTo.excess}</font></td>
            </tr>
        </c:if>
        <c:if test="${!mealTo.excess}">
            <tr>
                <td><font color="green">${date_time}</font></td>
                <td><font color="green">${mealTo.description}</font></td>
                <td><font color="green">${mealTo.calories}</font></td>
                <td><font color="green">${mealTo.excess}</font></td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
