import java.io.*;
import java.net.Socket;

enum ArduinoAction {
    TURN_ON("led1ON"),
    TURN_OFF("led1OFF");

    public final String label;

    ArduinoAction(String label) {
        this.label = label;
    }
}

public class SocketClient {

    private String host;
    private int port;
    private Socket socket;

    private InputStream inputStream;
    private DataOutputStream dataOutputStream;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void turnOn() throws IOException {
        this.sendAction(ArduinoAction.TURN_ON);
    }

    public void turnOff() throws IOException {
        this.sendAction(ArduinoAction.TURN_OFF);
    }

    public void sendAction(ArduinoAction action) throws IOException {
        // prima otteniamo il timestamp
        int timestamp = getTimestamp();

        this.sendMessage(action.label, timestamp);

        // ricordiamoci di chiudere il socket
        socket.close();
    }


    private String readUntilNewChar() throws IOException {
        int read;

        final char endMarker = '\n';

        StringBuilder messageBuffer = new StringBuilder();
        // reads to the end of the stream or till end of message
        while ((read = inputStream.read()) != -1) {
            char c = (char) read;
            // end?  jump out
            if (c == endMarker) {
                break;
            }
            // else, add to buffer
            messageBuffer.append(c);
        }

        return messageBuffer.toString();
    }


    /**
     * Tenta di connettersi la server
     *
     * @throws IOException
     */
    private int getTimestamp() throws IOException {
        System.out.println("Tentativo di connessione al server...");

        this.socket = new Socket(this.host, this.port);

        OutputStream outputStream = socket.getOutputStream();

        this.dataOutputStream = new DataOutputStream(outputStream);

        inputStream = socket.getInputStream();

        String timeStamp = readUntilNewChar();

        System.out.println("Ottenuto timestamp:  " + timeStamp);
        return Integer.parseInt(timeStamp);
    }

    private void sendMessage(String message, int timestamp) throws IOException {
        System.out.println("Inviando messaggio nel socket...");
        System.out.println("Payload del messaggio: " + message + timestamp);

        dataOutputStream.writeBytes(message + timestamp);

        String status = readUntilNewChar();
        System.out.println("Nuovo stato: " + status);
    }
}
