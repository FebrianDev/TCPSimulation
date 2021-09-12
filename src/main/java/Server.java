import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket server;
    private DataInputStream in;
    private DataOutputStream out;

    //start connection
    public void start(int port, int timeOut) {
        //init ServerSocket
        try {
            serverSocket = new ServerSocket(port);
            //set server timeout
            serverSocket.setSoTimeout(timeOut);
            //waiting for client
            System.out.println("Waiting for client on port ");
            server = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sync Ack / receive data from client
    public String receiveMessage() {
        String data = null;
        try {
            //getting Input Stream
            System.out.println("Connected to client "
                    + server.getRemoteSocketAddress()); //get client Socket
            in = new DataInputStream(server.getInputStream());
            data = in.readUTF();
            sendToClient(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Sync Ack / send data to client
    public String sendToClient(String data) {
        // write client message
        try {
            //getting output stream
            out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    //close server
    public void close() {
        //closing server
        try {
            if (server != null)
                server.close();
            if (in != null)
                in.close();
            if (serverSocket != null)
                serverSocket.close();
            if (out != null)
                out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

