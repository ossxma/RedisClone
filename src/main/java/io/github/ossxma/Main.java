package io.github.ossxma;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private final static int port = 9000;
    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(port);

        while (true) {
            try(Socket client = socket.accept()) {
                InputStream clientInputStream = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientInputStream));
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

                String line = null;
//                "*1\r\n$4\r\nPING\r\n"
                while ((line = reader.readLine()) != null) {
                    if (line.length() == 0)
                        continue;
                    System.out.println(line);
                    writer.println(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}