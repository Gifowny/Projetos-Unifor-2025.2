package app.app;
import service.api.api.DiscenteService;

public class TesteAPI {
    public static void main(String[] args) {
        DiscenteService service = new DiscenteService();
        String resposta = service.buscarDiscentes();
        System.out.println(resposta);
    }
}
