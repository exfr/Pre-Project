<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Поиск</title>
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
<div style="text-align: center;">
    <h1>
        <tr>
            <td>
                <caption>Поиск пользователя по ID</caption>
            </td>
        </tr>
        <tr>
            <td><b>Ведите ID:</b></td>
        </tr>
        <td colspan="2" align="center">
            <form action="${pageContext.request.contextPath}/findUser" method="GET">
                <input type="text" name="id" value='${users.id}'/>
                <input type="submit" value="Поиск"/>
            </form>
        </td>
    </h1>
</div>
</body>
</html>
