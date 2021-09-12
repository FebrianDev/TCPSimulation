import java.io.*;
import java.net.Socket;

public class Client {

    private Socket client;
    private DataInputStream in;
    private OutputStream outToServer;
    private DataOutputStream out;
    private InputStream inFromServer;

    // function for client connect to server
    public void start(int port, String serverName) {
        try {
            System.out.println("Connecting to "
                    + serverName
                    + " on port "
                    + port);
            //create socket and connect to server
            client = new Socket(serverName, port);
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    //function for client send data to server / SYNC
    public String sendMessage(String message) {
        String data = null;
        try {
            data = message;
            //create stream to send data to server
            if(client != null) {
                outToServer = client.getOutputStream();
                out = new DataOutputStream(outToServer);
                out.writeUTF(data);
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
        return data;
    }

    // function for client receive data from server / ACK
    public String receiveMessage() {
        String data = null;
        try {
            if(client != null) {
                //read InputStream from server
                inFromServer = client.getInputStream();
                in = new DataInputStream(inFromServer);
                data = in.readUTF();
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }

        return data;
    }

    //Close connection client
    public void close() {
        try {
            if (client != null)
                client.close();
            if (in != null)
                in.close();
            if (outToServer != null)
                outToServer.close();
            if (out != null)
                out.close();
            if (inFromServer != null)
                inFromServer.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
