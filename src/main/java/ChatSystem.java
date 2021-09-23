import java.util.ArrayList;
import java.util.List;

public class ChatSystem {
    List<Session> sessionList = new ArrayList<>();

    public Session createSession(String sessionName) {
        Session newSession = new Session(sessionName);
        sessionList.add(newSession);
        return newSession;
    }

    public Session getSessionByName(String sessionName) throws SessionException {
        return (Session) sessionList.stream()
                .filter(s -> s.getSessionName().equals(sessionName))
                .findFirst()
                .orElseThrow(() -> new SessionException("No session found!"));
    }

    public void addUserToSession(Session currentSession, User user) throws SessionException {
        currentSession.setUsers(user);
    }

    public void addUserToSession(Session currentSession, List<User> users) throws SessionException {
        currentSession.setUsers(users);
    }
}
