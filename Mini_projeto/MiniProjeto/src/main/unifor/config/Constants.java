package config;

public class Constants {

    // Mensagens de sucesso
    public static final String MSG_MATRICULA_SUCESSO = "Matrícula realizada com sucesso!";
    public static final String MSG_CANCELAMENTO_SUCESSO = "Cancelamento realizado com sucesso!";
    public static final String MSG_RESERVA_SUCESSO = "Reserva realizada com sucesso!";

    // Mensagens de erro
    public static final String MSG_DISCENTE_NAO_ENCONTRADO = "Discente não encontrado";
    public static final String MSG_DISCIPLINA_NAO_ENCONTRADA = "Disciplina não encontrada";
    public static final String MSG_LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
    public static final String MSG_SEM_VAGAS = "Disciplina sem vagas disponíveis";
    public static final String MSG_LIVRO_INDISPONIVEL = "Livro indisponível para reserva";
    public static final String MSG_LIMITE_DISCIPLINAS = "Limite de disciplinas atingido (máximo 5)";
    public static final String MSG_CURSO_DIFERENTE = "Disciplina pertence a curso diferente do discente";
    public static final String MSG_SITUACAO_TRANCADA = "Discente com situação acadêmica trancada";

    // Códigos de status HTTP
    public static final int HTTP_OK = 200;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_TIMEOUT = 408;
    public static final int HTTP_SERVER_ERROR = 500;

    private Constants() {
        throw new IllegalStateException("Classe utilitária");
    }
}
