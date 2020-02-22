package bean;

import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
public class GestionMessages {
    private static Map<Room, ArrayMessages> map = new HashMap<Room, ArrayMessages>();
    private Room room;

    public GestionMessages(){

    }

    public static Map<Room, ArrayMessages> getMap() {
        return map;
    }

    public static void setMap(Map<Room, ArrayMessages> map) {
        GestionMessages.map = map;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
        if(!GestionMessages.getMap().containsKey(this.room)){
            GestionMessages.map.put(this.room, new ArrayMessages());
        }
    }

    public void setListMessage(Room room, Message mess){
        map.get(room).getMessages().add(mess);
    }

    public ArrayMessages getListMessage(Room room){
        if(GestionMessages.getMap().containsKey(room)){
            return map.get(room);
        }else{
            return null;
        }

    }

    public int getNumberMessage(Room room){ return map.get(room).getMessages().size(); }

    public Message getLastMessage(Room room){
        int size = getNumberMessage(room);
        Message tmp = getListMessage(room).getMessages().get(size - 1);
        return tmp;
    }

    public void setLastMessage(Room room, Message message){
        int size = getNumberMessage(room);
        getListMessage(room).getMessages().add(size - 1, message);
    }

    public void deleteLastMessage(Room room){
        map.get(room).getMessages().remove(getNumberMessage(room) - 1);
    }
}
