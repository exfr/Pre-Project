<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Cписок пользователей</title>
</head>
<body>
<div style="text-align: center;">
    <h2>Страница пользователей</h2>
    <form action="${pageContext.request.contextPath}/users" method="GET">
        <input type="submit" value="Обновить">
    </form>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Зарегистрированные пользователи:</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Email</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/updateForm" method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="hidden" name="name" value="${user.name}"/>
                        <input type="hidden" name="password" value="${user.password}"/>
                        <input type="hidden" name="email" value="${user.email}"/>
                        <button type="submit">Изменить пользователя</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/delete" method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Удалить пользователя</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <table border="1" cellpadding="5">
        <tr>
            <td>
                <div style="text-align: center;">
                    <h3>Добавление пользователя</h3>
                </div>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/addForm" method="POST">
                    <input align="middle" type="hidden" name="action" value="add"/>
                    <button type="submit">Добавить</button>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <div style="text-align: center;">
                    <h3>Поиск пользователя</h3>
                </div>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/searchForm" method="POST">
                    <input align="middle" type="hidden" name="action" value="search"/>
                    <button type="submit">Искать</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>