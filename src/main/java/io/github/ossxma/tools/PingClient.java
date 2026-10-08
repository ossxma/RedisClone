package io.github.ossxma.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PingClient {
    public static void main(String args[]) throws IOException {
        try(Socket socket = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(args[0]))) {
            socket.setSoTimeout(500);
            var outputStream = socket.getOutputStream();
            var bytesReader = socket.getInputStream();
            byte[] buffer = new byte[256];
            outputStream.write("*1\r\n$4\r\nPING\r\n".getBytes(StandardCharsets.US_ASCII));

            ByteArrayOutputStream collected = new ByteArrayOutputStream();
            int bytesRead;
            try {
                while ((bytesRead = bytesReader.read(buffer)) > 0) {
                    collected.write(buffer, 0, bytesRead);
                }
            } catch (SocketTimeoutException e) {
                // treat timeout as if we received all data
            }
            byte[] fullReply = collected.toByteArray();
            System.out.println(Arrays.toString(fullReply));
        }
    }
}
