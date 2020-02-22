package back_office;

import bean.*;
import exception.IndexOutOfRangeException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/backOffice/ressourceMessage")
public class RessourceMessage {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView ressourceGetMessage(@RequestParam("room") String roomName,
                                            @RequestParam("idMess") int idMess,
                                            HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        Room room = new Room();
        room.setName(roomName);
        ArrayMessages messages = gestionMessages.getListMessage(room);

        if(idMess < messages.getMessages().size()){
            ModelAndView modelAndView = new ModelAndView("listMessagesRoom");
            String titre = "Messages(id:"+idMess+") Of " + roomName;
            modelAndView.addObject("titre", titre);
            modelAndView.addObject("message", messages.getMessages().get(idMess));
            return modelAndView;
        }else{
            String errCode = String.valueOf(HttpStatus.NOT_FOUND);
            String errMess = "Couldn't found message with ID out of range" + idMess;
            throw new IndexOutOfRangeException(String.valueOf(idMess), errCode, errMess);
        }
    }

    @RequestMapping(value = "/changeContentLastMessage", method = RequestMethod.POST)
    public ResponseEntity ressourceChangeContentLastMessage(@RequestParam("room") String roomName,
                                                  @RequestParam("idMess") String idMess,
                                                  @RequestParam("message") Message message,
                                                  HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        Room room = new Room();
        room.setName(roomName);
        if(Integer.parseInt(idMess) == gestionMessages.getNumberMessage(room) - 1){
            gestionMessages.setLastMessage(room, message);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            String errCode = String.valueOf(HttpStatus.BAD_REQUEST);
            String errMess = "Couldn't change content message with ID: " + idMess;
            throw new IndexOutOfRangeException(String.valueOf(idMess), errCode, errMess);
        }
    }

    @RequestMapping(value = "/deleteLastMessage", method = RequestMethod.DELETE)
    public ResponseEntity ressourceRemoveLastMessage(@RequestParam("room") String roomName,
                                                     @RequestParam("idMess") String idMess,
                                                     HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        Room room = new Room();
        room.setName(roomName);
        if(Integer.parseInt(idMess) == gestionMessages.getNumberMessage(room) - 1){
            gestionMessages.deleteLastMessage(room);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            String errCode = String.valueOf(HttpStatus.BAD_REQUEST);
            String errMess = "Couldn't  delete message with ID: " + idMess;
            throw new IndexOutOfRangeException(String.valueOf(idMess), errCode, errMess);
        }
    }

    @ExceptionHandler(IndexOutOfRangeException.class)
    public ModelAndView handleIndexOutOfRangeException(IndexOutOfRangeException ex){
        ModelAndView modelAndView = new ModelAndView("error/indexOutOfRange");
        modelAndView.addObject("exceptionCode", ex.getErrCode());
        modelAndView.addObject("exceptionMess", ex.getErrMsg());
        return modelAndView;
    }
}
