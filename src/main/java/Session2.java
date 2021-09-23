import java.util.List;

public interface Session2 {
    String getSessionName();

    void setSessionName(String sessionName);

    List<User> getUsers();

    void setUsers(List<User> users);

    void setUsers(User user);

    boolean gotActiveSession(User user);

    boolean sendMessage(User sender, User receiver, String message, Session session);

    List<Message> getMessages(User user);
}
