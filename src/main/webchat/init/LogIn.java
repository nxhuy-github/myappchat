package init;

import bean.GestionUserRoom;
import bean.User;
import bean.Room;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogIn extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        //System.out.println("doGet LogIn");
        if(session == null){
            //System.out.println("has NO session ");
            resp.sendRedirect("/");
        }
        else{
            //System.out.println("has session ");
            resp.sendRedirect("/createRoom.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");
        if(session.getAttribute("username") == null){
            String username = req.getParameter("username");
            //String roomName = req.getParameter("room");

            //Room room = new Room();
            //room.setName(roomName);

            User user = new User();
            user.setName(username);


            gestionUserRoom.setUser(user);
            session.setAttribute("username", user);
            //session.setAttribute("room", room);

        }
        String address = "/createRoom.html";
        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
