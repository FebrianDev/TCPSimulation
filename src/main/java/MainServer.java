public class MainServer {

    private static Server server;

    public static void main(String[] args) {
        server = new Server();
        server.start(2146, 60000);

        System.out.println("Client Says " + server.receiveMessage());
        server.close();
    }
}
