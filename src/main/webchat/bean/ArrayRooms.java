package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "arrayRooms")
public class ArrayRooms {
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayRooms() {
    }

    @XmlElement(name = "room")
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
