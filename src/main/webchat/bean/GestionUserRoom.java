package bean;

import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
public class GestionUserRoom {
    private static Map <User,ArrayRooms> listUsers = new HashMap<User, ArrayRooms>();
    private User user;

    public GestionUserRoom(){

    }

    public void ModifyUser(User oldName, User newName)
    {

        listUsers.put( newName, listUsers.get(oldName));
        listUsers.remove(oldName);
    }

    public static Map<User, ArrayRooms> getMap() {
        return listUsers;
    }
    public static void setMap(Map<User, ArrayRooms> map) {
        listUsers = map;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
        if(!GestionUserRoom.getMap().containsKey(this.user)){
            GestionUserRoom.listUsers.put(this.user, new ArrayRooms());
        }
    }

    public void setListRoom(User user, Room room){
        if(!listUsers.get(user).getRooms().contains(room)){
            listUsers.get(user).getRooms().add(room);
        }
    }

    public boolean checkUser(String userName) {
        boolean flag = false;
        for(Map.Entry<User,ArrayRooms> listUser : listUsers.entrySet())
        {
            if(userName != null && userName.equals(listUser.getKey().getName())) {
                flag = true;
            }
        }
        return flag;
    }

    public ArrayRooms getUserInList(User user){
        if(GestionUserRoom.getMap().containsKey(user)){
            return listUsers.get(user);
        }else{
            return null;
        }

    }

    public ArrayList<User> getListUser(){
        ArrayList<User> users = new ArrayList<User>();
        for(Map.Entry<User,ArrayRooms> listUser : listUsers.entrySet())
        {
            users.add(listUser.getKey());
        }
        return users;
    }

}
