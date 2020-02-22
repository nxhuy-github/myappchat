<%@ page import="bean.GestionListRoom" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="bean.Room" %>
<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Room</title>
</head>

<body>
<%
    ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    GestionListRoom gestionListRoom = (GestionListRoom)ctx.getBean("gestionListRoom");
    HttpSession s = request.getSession(false);
    String userName = ((User) s.getAttribute("username")).getName();
    String actionURL = "\"Chat\"";
    for(Room r : gestionListRoom.getArrayRooms().getRooms()){
        out.println("<form action=" + actionURL + " type=\"post\" target=\"_top\">\n" +
                "    <input type=\"hidden\" name=\"username\" value="+ userName +">\n" +
                "    <input type=\"hidden\" name=\"room\" value="+ r.getName()+">\n" +
                "    <input type=\"submit\" value="+ r.getName()+ ">\n" +
                "</form><br>");
        //out.println("<a href=\"https://www.w3schools.com/\">Click</a>");
    }
%>
</body>
</html>
