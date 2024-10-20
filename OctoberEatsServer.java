package Grupo4SC303MNProyectoClienteServidor;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class OctoberEatsServer {
    private static final int PORT = 12345;  // Puerto donde escuchará el servidor
    private static ExecutorService pool = Executors.newFixedThreadPool(10); // Para manejar varios clientes

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");
            // Manejo del cliente en un nuevo hilo
            pool.execute(new ClientHandler(clientSocket));
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Aquí iría la lógica de manejo del cliente
            out.println("Bienvenido a October Eats!");
            String request;
            while ((request = in.readLine()) != null) {
                // Manejar las peticiones del cliente
                System.out.println("Recibido del cliente: " + request);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
