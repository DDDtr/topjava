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
        <th>Id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <jsp:useBean id="mealToList" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealToList}">
        <javatime:format value="${mealTo.dateTime}" style="MS" var="date_time"/>

        <c:choose>
            <c:when test="${mealTo.excess}"><tr style="color: red"></c:when>
            <c:when test="${!mealTo.excess}"><tr style="color: green"></c:when>
        </c:choose>

        <td>${mealTo.id}</td>
        <td>${date_time}</td>
        <td>${mealTo.description}</td>
        <td>${mealTo.calories}</td>
        <td><a href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${mealTo.id}"/> ">Update</a></td>
        <td><a href="delete.jsp">Delete</a></td>

    </tr>
    </c:forEach>
</table>
</body>
</html>
