package bean;

import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class GestionListRoom {
    private ArrayRooms arrayRooms = new ArrayRooms();

    public ArrayRooms getArrayRooms() {
        return arrayRooms;
    }

    public void addRoom(Room room){
        if(!getArrayRooms().getRooms().contains(room)){
            getArrayRooms().getRooms().add(room);
        }
    }

    public boolean checkRoom(Room room){
        return getArrayRooms().getRooms().contains(room);
    }
}
