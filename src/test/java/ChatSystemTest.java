import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChatSystemTest {

    ChatSystem chatSystem;
    Session currentSession;
    List<User> users;
    String sessionName;

    User pelle;
    User svan;
    User userWithNoSession;

    @Before
    public void init() {
        pelle = new User("Pelle");
        svan = new User("Svan");
        userWithNoSession = new User("Morgan");

        chatSystem = new ChatSystem();
        users = new ArrayList<User>(Arrays.asList(pelle, svan));

        sessionName = "Session One";
        currentSession = chatSystem.createSession(sessionName);
    }

    /*
    Skapa en chatt session med en annan användare
    */
    @Test
    public void test_Create_session() throws SessionException {
        String localSessionName = "Local Session Name";
        Session localSession = chatSystem.createSession(localSessionName);

        assertEquals("Local Session Name", chatSystem.getSessionByName(localSessionName).getSessionName());
        assertThrows(SessionException.class, ()->{
            chatSystem.getSessionByName("Session None Existing");
        });
    }

    @Test
    public void test_add_users_to_session() throws SessionException {
        chatSystem.addUserToSession(currentSession, users);
        //chatSystem.addUserToSession(currentSession, Pelle);
        //chatSystem.addUserToSession(currentSession, Svan);

        users = currentSession.getUsers();
        assertTrue(currentSession.gotActiveSession(pelle));

        assertFalse(currentSession.gotActiveSession(userWithNoSession));
    }

    /*
    Skicka meddelande till andra användaren
    */
    @Test
    public void test_send_message() throws SessionException {
        // Send to a user that is not in session
        assertFalse(currentSession.sendMessage(pelle, userWithNoSession, "Hello from Pelle!", currentSession));

        // Add users to session
        chatSystem.addUserToSession(currentSession, users);

        // Pelle sends two messages to Svan
        currentSession.sendMessage(pelle, svan, "Hello from Pelle!", currentSession);
        currentSession.sendMessage(pelle, svan, "Hello from Pelle second msg!", currentSession);

        List<Message> response = svan.getMessages();
        assertEquals("Hello from Pelle!", response.get(0).toString());
        assertEquals("Hello from Pelle second msg!", response.get(1).toString());
    }


    /*
    Skicka att användaren påbörjat skriva i chatten.
    */


    /*
    Skicka avsluta chatt till servern
    */


    /*
    Mottag att andra användaren skickat meddelande eller
    påbörjat skriva i chatten. Alternativt att chatten avslutats.
     */


    /*
    Skapa funktionalitet så att kommunikationen mellan klienter kan genomföras över sockets
    */


    @Test
    public void test_given_when_then() throws SessionException {
        ChatSystem testChat = mock(ChatSystem.class);

        // GIVEN
        when(testChat.createSession(sessionName)).thenReturn(mock(Session.class));
        // WHEN
        Session newSession = new Session(sessionName);
        String result = newSession.getSessionName();
        // THEN
        assertEquals("Session One", result);
    }
}
