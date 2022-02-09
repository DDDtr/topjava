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

<h2>
    ${meal==null ? "Add new meal" : "Edit meal"}
</h2>
<form method="POST" action='add_meal' name="mealform">
    <input type="text" hidden readonly="readonly" name="id" value="${meal.id}"/> <br />
    Date time : <input type="datetime-local" name="datetime"  value="${meal.dateTime}"/> <br />
    Description : <input type="text" name="description" value="${meal.description}"/> <br />
    Calories : <input type="number" name="calories"  value="${meal.calories}"/> <br />
    <input type="submit" value="Submit" />
</form>

</body>
</html>

