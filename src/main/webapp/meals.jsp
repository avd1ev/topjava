<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a> </h3>
<hr>
<h2>Meals</h2>
<style>
    TABLE {
        width: 800px;
        border-collapse: collapse;
    }
    TD, TH {
        padding: 3px;
        border: 1px solid black;
    }
    TH {
        text-align: left; /* Выравнивание по левому краю */

    }
</style>
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
            <td>Update</td>
            <td>Delete</td>
            </tr>
        </c:forEach>

</table>
</body>
</html>
