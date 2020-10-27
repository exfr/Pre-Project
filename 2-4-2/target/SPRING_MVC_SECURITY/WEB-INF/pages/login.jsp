<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>

<div align="center">
    <h1>Вход в личный кабинет</h1>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <input name="username" placeholder="ЛОГИН"/>
        <input name="password" placeholder="ПАРОЛЬ"/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>
