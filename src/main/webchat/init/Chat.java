package init;

import bean.GestionUserRoom;
import bean.Room;
import bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Chat extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I'm HERE in GET");
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");
        HttpSession session = req.getSession(false);
        if(session != null){
            String roomName = req.getParameter("room");
            Room room = new Room();
            room.setName(roomName);

            session.setAttribute("room", room);
            gestionUserRoom.setListRoom((User)session.getAttribute("username"), room);
            String address = "interface.html";
            RequestDispatcher dispatcher = req.getRequestDispatcher(address);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I'm HERE in POST");

    }
}
