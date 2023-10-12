<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h3><a href="meals">Add meal</a></h3>
<style>
    table,th,td
    {
        border-style: solid;
        border-width: 1px;
        box-sizing: border-box;
        padding: 5px 10px 5px 10px;
        /*border:1px solid black;*/
    }
</style>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${mealsTo}" var="meal">

        <tr>
            <c:choose>
        <c:when test="${meal.isExcess()}">
            <c:set var="color" value="red" />
        </c:when>
                <c:otherwise>
                    <c:set var="color" value="MediumSeaGreen" />
                </c:otherwise>
            </c:choose>
            <td><span style="color: ${color}; ">${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</span></td>
            <td><span style="color: ${color}; ">${meal.getDescription()}</span></td>
            <td><span style="color: ${color}; ">${meal.getCalories()}</span></td>
            <td><a href="meals">update</a></td>
            <td><a href="meals">delete</a></td>

        </tr>

    </c:forEach>
</table>
</body>
</html>
