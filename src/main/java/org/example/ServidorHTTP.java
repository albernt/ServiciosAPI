package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorHTTP {
    public static void main(String[] args) throws IOException {
        int puerto = 8080;
        HttpServer servidor = HttpServer.create(new InetSocketAddress(puerto), 0);

        servidor.createContext("/saludo", new ManejadorSaludo());
        servidor.setExecutor(null);
        servidor.start();

        System.out.println("🚀 Servidor iniciado en http://localhost:" + puerto + "/saludo");
    }
}

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
