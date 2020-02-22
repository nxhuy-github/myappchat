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
    <title>List Rooms</title>
</head>
<h1 id="${titre}">List Room Of User ${titre}</h1>
<c:forEach items="${arrayRoom.rooms}" var="room">
    <p>${room.name}</p>
</c:forEach>

</body>
</html>
