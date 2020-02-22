package filter;

import bean.GestionUserRoom;
import bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class Filter implements javax.servlet.Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");

        String userName = request.getParameter("username");
        boolean isURLBase = request.getRequestURI().toString().equalsIgnoreCase("/");
        boolean isURLChat = request.getRequestURI().equalsIgnoreCase("/Chat");
        boolean isURLCreateRoom = request.getRequestURI().equalsIgnoreCase("/CreateRoom");
        boolean checkUserInUserRoom = gestionUserRoom.checkUser(userName);


        if(isURLBase){
            //System.out.println("blabla");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(isURLChat){
            if(userName != null && checkUserInUserRoom){
                System.out.println("Go to CHAT");
                //System.out.println("blublu");
                System.out.println("Check User in list: "+ checkUserInUserRoom);
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                //System.out.println("bloblo");
                response.sendRedirect("/");
                return;
            }
        }else if(isURLCreateRoom){
            if(userName != null){
                //System.out.println("blzblz");
                System.out.println(userName);
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                HttpSession session = request.getSession(false);
                String sessionUser = ((User)session.getAttribute("username")).getName();
                boolean checkSessionUser = gestionUserRoom.checkUser(sessionUser);
                if(sessionUser != null && checkSessionUser){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else{
                    //System.out.println("blwblw");
                    response.sendRedirect("/");
                    return;
                }
            }
        }else if((isURLChat || isURLCreateRoom) && request.getSession(false) != null){
            //System.out.println("bleble");
           filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //System.out.println("blibli");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        //System.out.println(request.getRequestURI());
    }

    public void destroy() {

    }
}
