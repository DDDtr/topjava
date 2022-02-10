<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Meals</h1>

<a href="add_meal.jsp">Add Meal</a>
<br><br>
<style type="text/css">
    Table {
        border-collapse: collapse;
        border: 2px solid black;
    }

    TD, TH {
        padding: 5px;
        border: 2px solid black;
    }
</style>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="mealTo" items="${mealToList}">
        <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm" style="MS" var="date_time"/>
        <tr style="color:${mealTo.excess ? "red" : "green"}">
            <td>${date_time}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="${pageContext.request.contextPath}/meals?action=update&id=${mealTo.id}">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=${mealTo.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
