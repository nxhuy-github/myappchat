<%@ page import="bean.GestionMessages" %>
<%@ page import="bean.Room" %>
<%@ page import="bean.User" %>
<%@ page import="bean.Message" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 10/30/2017
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mon appli de Chat</title>
</head>
<body>
<%
    String messageStr = request.getParameter("messageUser");
    HttpSession s = request.getSession();
    ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
//    GestionMessages gestionMessages = (GestionMessages)application.getAttribute("gestionMessages");

    Room room = (Room)s.getAttribute("room");
    gestionMessages.setRoom(room);

    if(messageStr != null) {
        Message mess = new Message();
        mess.setUser((User) s.getAttribute("username"));
        mess.setMessage(messageStr);

        gestionMessages.setListMessage(room, mess);
    }
    //System.out.println(gestionMessages.getNumberMessage(room));
%>
</body>
</html>
