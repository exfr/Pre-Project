<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Добавление пользователя</title>
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
    <form action="/add" method="GET" accept-charset="UTF-8">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Добавление нового пользователя:
                </h2>
            </caption>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                    />
                </td>
            </tr>
            <tr>
                <th>User Password:</th>
                <td>
                    <input type="text" name="password" size="45"
                    />
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" size="45"
                    />
                </td>
            </tr>
            <td colspan="2" align="center">
                <input type="submit" value="Добавить"/>
            </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>