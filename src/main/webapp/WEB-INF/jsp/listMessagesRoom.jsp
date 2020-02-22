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
<h1>${titre}</h1>
<c:if test="${not empty message}">
    <h2>${message.user.name}: ${message.message}</h2>
</c:if>
<h3>${numberMessage}</h3>
<c:forEach items="${messages.messages}" var="mess">
    ${mess.user.name}: ${mess.message}<br>
</c:forEach>
</body>
</html>
