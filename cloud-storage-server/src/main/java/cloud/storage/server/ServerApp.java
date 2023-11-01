package cloud.storage.server;

import cloud.storage.server.server.Server;

public class ServerApp implements Runnable {
    private final Server server;

    public ServerApp(Server server) {
        this.server = server;
    }
    @Override
    public void run() {
        server.start();
    }

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp(new Server());
        serverApp.run();
    }
}
