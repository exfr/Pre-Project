<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Редактирование пользователя</h1>
    <h2>
        <form action="/" method="GET">
            <input type="submit" value="List All Users">
        </form>
    </h2>
</div>
<div align="center">
    <form action="${pageContext.request.contextPath}/update" method="POST">
        <table border="1" cellpadding="5">
            <tr>
                <th>User ID:</th>
                <td>
                    <input type="text" name="id" readonly size="45"
                           value="<c:out value='${user.id}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${user.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Password:</th>
                <td>
                    <input type="text" name="password" size="45"
                           value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${user.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <form action="${pageContext.request.contextPath}/users" method="GET">
                        <input type="submit" value="Сохранить изменения"/>
                    </form>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
