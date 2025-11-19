package external.client;

public interface HttpClient {
    String get(String url) throws Exception;
    String post(String url, String body) throws Exception;
}
