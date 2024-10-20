package Grupo4SC303MNProyectoClienteServidor;

import java.io.*;
import java.net.*;

public class OctoberEatsClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Envío y recepción de mensajes del servidor
            out.println("Quiero hacer un pedido.");
            String response = in.readLine();
            System.out.println("Servidor: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
