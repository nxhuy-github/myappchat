<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="bean.GestionListRoom" %>
<%@ page import="bean.Room" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/8/2017
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Room</title>
</head>
<body>
<%
    String roomName = request.getParameter("room");
    ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    GestionListRoom gestionListRoom = (GestionListRoom)ctx.getBean("gestionListRoom");

    if(roomName != null){
        Room room = new Room();
        room.setName(roomName);
        gestionListRoom.addRoom(room);
    }
%>
</body>
</html>
