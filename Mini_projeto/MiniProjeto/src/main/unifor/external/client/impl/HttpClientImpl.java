package external.client.impl;


import config.ApplicationConfig;
import external.client.HttpClient;
import util.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Implementação do cliente HTTP usando HttpURLConnection nativo do Java.
 *
 * Padrões aplicados:
 * - SOLID: DIP - Depende da abstração HttpClient
 * - GoF: Template Method - Estrutura comum para requisições
 */
public class HttpClientImpl implements HttpClient {

    private static final Logger logger = Logger.getInstance();
    private final ApplicationConfig config;

    public HttpClientImpl() {
        this.config = ApplicationConfig.getInstance();
    }

    @Override
    public String get(String urlString) throws Exception {
        long startTime = System.currentTimeMillis();
        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(config.getConnectionTimeout());
            connection.setReadTimeout(config.getReadTimeout());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            long duration = System.currentTimeMillis() - startTime;

            if (duration > config.getReadTimeout()) {
                logger.warn(String.format("⚠️  Requisição levou %dms (limite: %dms)",
                        duration, config.getReadTimeout()));
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readResponse(connection);
            } else {
                throw new Exception("HTTP Error: " + responseCode);
            }

        } catch (SocketTimeoutException e) {
            logger.error("Timeout ao acessar: " + urlString);
            throw new Exception("Timeout na requisição", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public String post(String urlString, String body) throws Exception {
        long startTime = System.currentTimeMillis();
        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(config.getConnectionTimeout());
            connection.setReadTimeout(config.getReadTimeout());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            if (body != null) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                connection.getOutputStream().write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            long duration = System.currentTimeMillis() - startTime;

            if (duration > config.getReadTimeout()) {
                logger.warn(String.format("⚠️  Requisição levou %dms (limite: %dms)",
                        duration, config.getReadTimeout()));
            }

            if (responseCode == HttpURLConnection.HTTP_OK ||
                    responseCode == HttpURLConnection.HTTP_CREATED) {
                return readResponse(connection);
            } else {
                throw new Exception("HTTP Error: " + responseCode);
            }

        } catch (SocketTimeoutException e) {
            logger.error("Timeout ao acessar: " + urlString);
            throw new Exception("Timeout na requisição", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String readResponse(HttpURLConnection connection) throws Exception {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}

