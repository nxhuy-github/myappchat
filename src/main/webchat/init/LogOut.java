package init;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;

        // Get an array of Cookies associated with the this domain
        cookies = request.getCookies();

        if( cookies != null ) {


            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];

                if((cookie.getName( )).compareTo("messaNumber") == 0 ) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }

            }
        } else {
        }
        System.out.println("Log Out");
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("room");
        session.invalidate();
        response.sendRedirect("/");
        return;
    }
}
