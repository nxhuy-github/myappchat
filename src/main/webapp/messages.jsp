<%@ page import="java.util.List" %>
<%@ page import="bean.Message" %>
<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Room" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 10/3/2017
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mon appli de Chat</title>
</head>
<body>
<%--
<%!
    List<Message> messages = new ArrayList<Message>();
    List<Room> rooms = new ArrayList<Room>();
%>
<%
    response.setIntHeader("Refresh", 5);

    String messageStr = request.getParameter("messageUser");
    HttpSession s = request.getSession();

    Message mess = new Message();
    mess.setUser((User) s.getAttribute("username"));
    mess.setMessage(messageStr);

    System.out.println(mess.getUser().getName());

    messages.add(mess);

%>

<% for (int i = 0; i < messages.size(); i++) { %>
    <% if (messages.get(i).getMessage() != null &&
            messages.get(i).getUser().getRoom().equals(((User) s.getAttribute("username")).getRoom())) { %>
        <%= messages.get(i).getUser().getName() %> : <%= messages.get(i).getMessage() %><br>
    <% } %>
<% } %>
--%>

<%--<jsp:useBean id="gestionMessages" class="bean.GestionMessages" scope="application"/>--%>
<%
    response.setIntHeader("Refresh", 5);
%>
<jsp:include page="Stockage.jsp" />
<jsp:forward page="Affichage.jsp" />
<%--
    response.setIntHeader("Refresh", 5);

    String messageStr = request.getParameter("messageUser");
    HttpSession s = request.getSession();

    Message mess = new Message();
    mess.setUser((User) s.getAttribute("username"));
    mess.setMessage(messageStr);

    Room room = new Room();
    room = (Room)s.getAttribute("room");

    gestionMessages.setRoom(room);
    gestionMessages.setListMessage(room, mess);

    System.out.println(room.getName());

    for(Message m : gestionMessages.getListMessage(room)){
        if(m.getMessage() != null){
            out.println(m.getUser().getName() +" : "+m.getMessage() + "<br>");
        }
    }
--%>

</body>
</html>
