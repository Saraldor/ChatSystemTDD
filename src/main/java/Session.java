import java.util.ArrayList;
import java.util.List;

// Session as interface
// Keep track och messages in the Session
public class Session implements Session2 {
    String sessionName;
    List<User> users = new ArrayList<>();

    public Session(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users.addAll(users);
    }

    @Override
    public void setUsers(User user) {
        this.users.add(user);
    }

   /* public boolean gotActiveSession(String userName) {
        //  Svan.getMessages().forEach(m -> System.out.println(m.toString()));
        return users.stream().anyMatch(map -> map.getName().equals(userName));
    }*/

    @Override
    public boolean gotActiveSession(User user) {
        return users.stream().anyMatch(map -> map.equals(user));
    }

    @Override
    public boolean sendMessage(User sender, User receiver, String message, Session session) {
        if(gotActiveSession(receiver)) {
            sender.sendMessage(sender, receiver, message, session);
            receiver.sendMessage(sender, receiver, message, session);
            return true;
       }
       return false;
    }

    @Override
    public List<Message> getMessages(User user) {
        return user.getMessages();
    }
}
