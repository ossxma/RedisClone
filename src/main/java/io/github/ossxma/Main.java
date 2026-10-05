package io.github.ossxma;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private final static int port = 9000;
    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(port);

        while (true) {
            Socket client = socket.accept();
            InputStream clientInputStream = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientInputStream));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            String line = null;
            while ((line = reader.readLine()).length() > 0) {
                System.out.println(line);
                writer.println(line);
            }
        }
    }
}