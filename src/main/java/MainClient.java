public class MainClient {

    private static Client client;

    public static void main(String[] args) {
        client = new Client();
        client.start(2146, "localhost");
        System.out.println("Client says " + client.sendMessage("Febrian"));
        System.out.println("Server says " + client.receiveMessage());
        client.close();
    }
}
