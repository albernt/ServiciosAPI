package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorHTTP {
    public static void main(String[] args) throws IOException {
        int puerto = 8080;  // Cambia el puerto si es necesario
        HttpServer servidor = HttpServer.create(new InetSocketAddress(puerto), 0);

        // Manejador para la ruta /saludo
        servidor.createContext("/saludo", new ManejadorSaludo());
        servidor.setExecutor(null); // Usa el executor por defecto
        servidor.start();

        System.out.println("🚀 Servidor iniciado en http://localhost:" + puerto + "/saludo");
    }
}

// Manejador que responde a las solicitudes
class ManejadorSaludo implements HttpHandler {
    @Override
    public void handle(HttpExchange intercambio) throws IOException {
        String respuesta = "¡Hola, cliente!";
        intercambio.sendResponseHeaders(200, respuesta.getBytes().length);

        OutputStream salida = intercambio.getResponseBody();
        salida.write(respuesta.getBytes());
        salida.flush();
        salida.close();
    }
}
