package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Message {
    private User user;
    private String message;

    public Message() {}

    public User getUser() {
        return user;
    }

    @XmlElement(name = "user")
    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }
}
