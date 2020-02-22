<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/6/2017
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Room</title>
</head>
<body>
<h1>All Room</h1>
<c:forEach items="${listRoom.rooms}" var="room">
    <h4>- ${room.name}</h4><br>
</c:forEach>
</body>
</html>
