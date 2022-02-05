<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/Update meal</title>
</head>
<body>
<hr>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="meals">Meals</a></li>
</ul>

<%--<form method="POST" action="create" name="action">--%>

<%--    <input type="number" value="4" readonly><br>--%>

<%--    <label>Date and time--%>
<%--        <input type="datetime-local" name="datetime">--%>
<%--    </label>--%>
<%--    <br>--%>
<%--    <label>Description--%>
<%--        <input type="text" name="description">--%>
<%--    </label>--%>
<%--    <br>--%>
<%--    <label>Calories--%>
<%--        <input type="number" name="calories">--%>
<%--    </label>--%>
<%--    <br>--%>
<%--    <input type="submit" value="Send"><br>--%>

<%--</form>--%>

<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="POST" action='meals' name="mealform">
    Meal Id : <input type="text" readonly="readonly" name="id" value="<c:out value="${meal.id}" />" /> <br />
    Date time : <input type="datetime-local" name="datetime"  value="<c:out value="${meal.dateTime}" />" /> <br />
    Description : <input type="text" name="description" value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input type="text" name="calories"  value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>

</body>
</html>

