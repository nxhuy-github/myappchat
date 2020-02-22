package back_office;


import bean.*;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/backOffice/ressourceUser")
public class RessourceUser {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView ressourceListRoom(@RequestParam("user") String userName, HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");

        User newUser = new User();
        newUser.setName(userName);
        ArrayRooms rooms;
        if(gestionUserRoom.checkUser(userName))
        {
             rooms = gestionUserRoom.getUserInList(newUser) ;
        }
        else
        {
             rooms = null;
        }
        ModelAndView modelAndView = new ModelAndView("listUsersRoom");
        String titre = userName;
        modelAndView.addObject("titre", titre);
        modelAndView.addObject("arrayRoom", rooms);
        return modelAndView;
    }


    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public ResponseEntity ressourceChangeNameUser(@RequestParam("oldName") String oldUserName,
                                                    @RequestParam("newName") String newUserName,
                                                    HttpServletRequest request) {
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");
        HttpStatus httpStatus;


        User user = new User();
        user.setName(oldUserName);
        User modifyUser = new User();
        modifyUser.setName(newUserName);
        if(gestionUserRoom.checkUser(oldUserName)){
            httpStatus = HttpStatus.OK;
            gestionUserRoom.ModifyUser(user,modifyUser);
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(httpStatus);
    }

}
