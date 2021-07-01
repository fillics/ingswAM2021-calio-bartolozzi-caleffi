package it.polimi.ingsw.server;

/**
 * Class used to get the server's information from the json file ClientConnection.json when a client runs the Client app
 * with the parameter -default
 */
public class ArgsConnection {
    private String serverPort, ipAddress;

    public ArgsConnection(String serverPort, String ipAddress) {
        this.serverPort = serverPort;
        this.ipAddress = ipAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
