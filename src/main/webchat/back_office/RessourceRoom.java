package back_office;

import bean.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.DataMalformedException;
import exception.IndexOutOfRangeException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/backOffice/ressourceRoom")
public class RessourceRoom {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView ressourceListMessageRoom(@RequestParam("room") String roomName, HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");

        Room room = new Room();
        room.setName(roomName);
        ArrayMessages messages = gestionMessages.getListMessage(room);

        ModelAndView modelAndView = new ModelAndView("listMessagesRoom");
        String titre = "List Messages Of " + roomName;
        modelAndView.addObject("titre", titre);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/numberMessageOfRoom", method = RequestMethod.GET)
    public ModelAndView ressourceGetNumberMessageRoom(@RequestParam("room") String roomName,
                                                      HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        Room room = new Room();
        room.setName(roomName);
        ModelAndView modelAndView = new ModelAndView("listMessagesRoom");
        String titre = "Messages's Number Of " + roomName;
        modelAndView.addObject("titre", titre);
        modelAndView.addObject("numberMessage", gestionMessages.getNumberMessage(room));
        return modelAndView;
    }

    @RequestMapping(value = "/listMessBeginWith", method = RequestMethod.GET)
    public ModelAndView ressourceGetListMessageRoomById(@RequestParam("room") String roomName,
                                                        @RequestParam("idMess") int idMess,
                                                        HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");

        Room room = new Room();
        room.setName(roomName);
        ArrayMessages messages = gestionMessages.getListMessage(room);
        ArrayMessages tmp = new ArrayMessages();
        if(idMess >= messages.getMessages().size()){
            String errCode = String.valueOf(HttpStatus.NOT_FOUND);
            String errMess = "Index " + idMess + " out of range";
            throw new IndexOutOfRangeException(String.valueOf(idMess), errCode, errMess);
        }
        for(int i = idMess; i < messages.getMessages().size(); i++){
            tmp.getMessages().add(messages.getMessages().get(i));
        }
        ModelAndView modelAndView = new ModelAndView("listMessagesRoom");
        String titre = "List Messages Of " + roomName;
        modelAndView.addObject("titre", titre);
        modelAndView.addObject("messages", tmp);
        return modelAndView;
    }

    @RequestMapping(value = "/addMessageToRoom", method = RequestMethod.POST)
    public ResponseEntity ressourceAddMessageToRoom(@RequestParam("room") String roomName,
                                                  @RequestParam("message") String message,
                                                  HttpServletRequest request) {
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        HttpStatus httpStatus;
        Room room = new Room();
        room.setName(roomName);

        System.out.println("Post: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Message addedMess;
        try {
            addedMess = objectMapper.readValue(message, Message.class);
            httpStatus = HttpStatus.OK;
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
            throw new DataMalformedException(message);
        }
        gestionMessages.setListMessage(room, addedMess);
        return new ResponseEntity(httpStatus);
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public ResponseEntity ressourceAddRoom(@RequestParam("roomName") String roomName,
                                           HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionListRoom gestionListRoom = (GestionListRoom)ctx.getBean("gestionListRoom");
        Room room = new Room();
        room.setName(roomName);
        if(!gestionListRoom.checkRoom(room)){
            gestionListRoom.addRoom(room);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.DELETE)
    public ResponseEntity ressourceDeleteRoom(@RequestParam("room") String roomName, HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");

        Room room = new Room();
        room.setName(roomName);

        gestionMessages.getMap().remove(room);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/addUserToRoom", method = RequestMethod.POST)
    public ResponseEntity ressourceAddUserToRoom(@RequestParam("roomName") String roomName,
                                           @RequestParam("user") String userName,
                                           HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionUserRoom gestionUserRoom = (GestionUserRoom)ctx.getBean("gestionUserRoom");
        GestionMessages gestionMessages = (GestionMessages)ctx.getBean("gestionMessages");
        Room room = new Room();
        room.setName(roomName);
        User user = new User();
        user.setName(userName);
        if(gestionUserRoom.checkUser(userName)){
            gestionUserRoom.setListRoom(user, room);
            gestionMessages.setRoom(room);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/allRooms", method = RequestMethod.GET)
    public ModelAndView ressourceAllRooms(HttpServletRequest request){
        ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        GestionListRoom gestionListRoom = (GestionListRoom)ctx.getBean("gestionListRoom");
        ModelAndView modelAndView = new ModelAndView("listRoom");
        modelAndView.addObject("listRoom", gestionListRoom.getArrayRooms());
        return modelAndView;
    }

    @ExceptionHandler(DataMalformedException.class)
    public ModelAndView handleDataMalformedException(HttpServletRequest request, Exception ex){
        ModelAndView modelAndView = new ModelAndView("error/dataMalformed");
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }

    @ExceptionHandler(IndexOutOfRangeException.class)
    public ModelAndView handleIndexOutOfRangeException(IndexOutOfRangeException ex){
        ModelAndView modelAndView = new ModelAndView("error/indexOutOfRange");
        modelAndView.addObject("exceptionCode", ex.getErrCode());
        modelAndView.addObject("exceptionMess", ex.getErrMsg());
        return modelAndView;
    }
}
