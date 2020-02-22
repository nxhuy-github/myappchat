<%--
  Created by IntelliJ IDEA.
  User: DEFAULT
  Date: 11/26/2017
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Add User</p>
<form action="/dispatcher/users/ListUser" method="post">
    <label></label><input name="userName" type="text" required>
    <input type="submit">
</form>
</body>
</html>
