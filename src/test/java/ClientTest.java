import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientTest {
    private Client client;
    private final int PORT = 2146;
    private final String IP = "localhost";

    public static String data = "Febrian";

    public ClientTest() {
        client = new Client();
    }

    @Test
    public void sendMessage() {
        client.start(PORT, IP);
        String send = client.sendMessage(data);
        assertNotNull(send);
        assertEquals(send, data);

        System.out.println("Client says " + send);

        String receive = client.receiveMessage();
        assertNotNull(receive);
        assertEquals(receive, data);

        System.out.println("Server says " + receive);
    }

    @Test
    public void close() {
        client.close();
    }
}
