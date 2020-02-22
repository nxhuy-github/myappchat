package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "arrayMessages")
public class ArrayMessages {
    private ArrayList<Message> messages = new ArrayList<Message>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayMessages() {
    }

    @XmlElement(name = "message")
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
