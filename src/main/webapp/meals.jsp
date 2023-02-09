<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/meals.css"/>
</head>
<body>
<h3><a href="index.html">Home</a> </h3>
<hr>
<h2>Meals</h2>
<a href=""><h4>Add meals</h4></a>
<table>
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

        <c:set var="mealsTo" value="${requestScope.get('list_mealsTo')}"/>
        <c:forEach var="item" items="${mealsTo}">
            <tr
            <c:choose>
                <c:when test="${item.isExcess()}">
                    style="color: red"
                </c:when>
                <c:otherwise>
                    style="color: green"
                </c:otherwise>
            </c:choose>
            >
            <td>${f:formatLocalDateTime(item.getDateTime(), 'dd.MM.yyyy hh:mm')}</td>
            <td>${item.getDescription()}</td>
            <td>${item.getCalories()}</td>
            <td><a href="">Update</a></td>
            <td><a href="">Delete</a></td>
            </tr>
        </c:forEach>

</table>
</body>
</html>
