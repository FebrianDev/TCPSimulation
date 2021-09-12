import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    private Server server;
    private final int PORT = 2146;
    private final int TIMEOUT = 60000;

    public ServerTest(){
        server = new Server();
    }

    @Test
    public void receiveMessage(){
        server.start(PORT, TIMEOUT);
        String receive = server.receiveMessage();
        assertEquals(receive, ClientTest.data);
    }

    @Test
    public void close(){
        server.close();
    }
}
