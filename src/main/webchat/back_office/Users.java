package back_office;

import bean.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/users")
public class Users {

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView showPageAddUser( HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("addUser");
        return modelAndView;
    }

    @RequestMapping(value = "/ListUser", method = RequestMethod.POST)
    public ModelAndView createAndShowlistUsers(@RequestParam(value = "userName", required = false) String userName,
                                               HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("listUsers");
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");

        User newUser = new User();
        newUser.setName(userName);
        gestionUserRoom.setUser(newUser);


        ArrayList<User> users = gestionUserRoom.getListUser();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
