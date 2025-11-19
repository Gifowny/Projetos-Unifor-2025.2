package config;

/**
 * Configuração central da aplicação (Singleton).
 *
 * Padrões aplicados:
 * - GoF: Singleton - Instância única de configuração
 * - SOLID: SRP - Responsável apenas por configurações
 */
public class ApplicationConfig {

    private static ApplicationConfig instance;

    // URLs dos microsserviços
    private final String discenteServiceUrl;
    private final String disciplinaServiceUrl;
    private final String bibliotecaServiceUrl;

    // Configurações de timeout
    private final int connectionTimeout;
    private final int readTimeout;

    // Configurações de negócio
    private final int maxDisciplinasPorDiscente;
    private final int diasEmprestimoLivro;

    private ApplicationConfig() {
        this.discenteServiceUrl = "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno";
        this.disciplinaServiceUrl = "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";
        this.bibliotecaServiceUrl = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";
        this.connectionTimeout = 5000; // 5 segundos
        this.readTimeout = 3000; // 3 segundos (requisito do PDF)
        this.maxDisciplinasPorDiscente = 5;
        this.diasEmprestimoLivro = 14;
    }

    public static synchronized ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public String getDiscenteServiceUrl() { return discenteServiceUrl; }
    public String getDisciplinaServiceUrl() { return disciplinaServiceUrl; }
    public String getBibliotecaServiceUrl() { return bibliotecaServiceUrl; }
    public int getConnectionTimeout() { return connectionTimeout; }
    public int getReadTimeout() { return readTimeout; }
    public int getMaxDisciplinasPorDiscente() { return maxDisciplinasPorDiscente; }
    public int getDiasEmprestimoLivro() { return diasEmprestimoLivro; }
}
