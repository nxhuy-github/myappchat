<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/18/2017
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>List messages</title>
</head>
<body>
<h1>List  Users</h1>
<c:forEach items="${users}" var="user">
    <h1>${user.name}</h1><br>
</c:forEach>



</body>
</html>
