<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Результат поиска</title>
</head>
<body>
<div style="text-align: center;">
    <h2>Вернуться на страницу списка пользователей</h2>
    <h2>
        <form action="${pageContext.request.contextPath}/users" method="GET">
            <input type="submit" value="Список пользователей">
        </form>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Найден пользователь:</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Email</th>
        </tr>
        <c:forEach var="user" items="${findUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.email}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
