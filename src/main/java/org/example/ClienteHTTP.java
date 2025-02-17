package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHTTP {
    public static void main(String[] args) {
        String url = "http://localhost:8080/saludo";

        try {
            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest solicitudGet = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> respuestaGet = cliente.send(solicitudGet, HttpResponse.BodyHandlers.ofString());

            System.out.println("📩 GET - Código de respuesta: " + respuestaGet.statusCode());
            System.out.println("📩 GET - Respuesta del servidor: " + respuestaGet.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
