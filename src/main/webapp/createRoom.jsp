
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Room</title>
</head>
<body>
<%
    response.setIntHeader("Refresh", 5);
%>
<jsp:include page="StockageRoom.jsp" />
<jsp:forward page="AffichageRoom.jsp" />
</body>
</html>
