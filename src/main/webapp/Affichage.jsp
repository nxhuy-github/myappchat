<%@ page import="bean.GestionMessages" %>
<%@ page import="bean.Message" %>
<%@ page import="bean.Room" %>
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
    boolean flag = true;
    Cookie cookie;
    Cookie[] cookies;
    cookies = request.getCookies();

    ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
    //GestionMessages gestionMessages = (GestionMessages)application.getAttribute("gestionMessages");

    Room room = (Room)request.getSession().getAttribute("room");
    int size = gestionMessages.getListMessage(room).getMessages().size();

    for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i];
        if((cookie.getName( )).equals("messaNumber")) {
            flag = false;
            if(Integer.parseInt(cookie.getValue()) < size)
            {
                for(Message m : gestionMessages.getListMessage(room).getMessages()) {
                    out.println(m.getUser().getName() + " : " + m.getMessage() + "<br>");
                }
                cookie.setValue(Integer.toString(size));
                response.addCookie(cookie);
            }
            else
            {
                response.setStatus(304);
            }
        }
    }

    if(flag)
    {
        Cookie messaNumber = new Cookie("messaNumber", "0");
        response.addCookie( messaNumber  );
    }

%>

</body>
</html>
