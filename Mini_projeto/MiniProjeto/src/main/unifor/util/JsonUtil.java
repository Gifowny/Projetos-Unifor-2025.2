package util;


public class JsonUtil {


    public static String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }

        // Se for String, retorna com aspas
        if (obj instanceof String) {
            return "\"" + escapeJson((String) obj) + "\"";
        }

        // Se for número ou boolean, retorna direto
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }

        // Para outros objetos, usa reflexão simples
        StringBuilder json = new StringBuilder("{");

        try {
            java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
            boolean first = true;

            for (java.lang.reflect.Field field : fields) {
                // Ignora campos estáticos
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(obj);

                if (!first) {
                    json.append(",");
                }
                first = false;

                json.append("\"").append(field.getName()).append("\":");

                if (value == null) {
                    json.append("null");
                } else if (value instanceof String) {
                    json.append("\"").append(escapeJson((String) value)).append("\"");
                } else if (value instanceof Number || value instanceof Boolean) {
                    json.append(value);
                } else {
                    // Para objetos complexos, chama recursivamente
                    json.append(toJson(value));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao converter para JSON", e);
        }

        json.append("}");
        return json.toString();
    }


    public static <T> T fromJson(String json, Class<T> classOfT) {
        // Esta é uma implementação MUITO simplificada
        // Para o projeto, recomendo fortemente usar Gson
        throw new UnsupportedOperationException(
                "fromJson não implementado na versão nativa. " +
                        "Use Gson: JsonUtil com Gson é mais robusto e recomendado."
        );
    }


    private static String escapeJson(String value) {
        if (value == null) {
            return null;
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }


    public static String prettify(String json) {
        if (json == null || json.isEmpty()) {
            return json;
        }

        StringBuilder pretty = new StringBuilder();
        int indent = 0;
        boolean inString = false;

        for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                    pretty.append(c);
                    if (!inString) {
                        indent++;
                        pretty.append('\n').append("  ".repeat(indent));
                    }
                    break;
                case '}':
                case ']':
                    if (!inString) {
                        indent--;
                        pretty.append('\n').append("  ".repeat(indent));
                    }
                    pretty.append(c);
                    break;
                case ',':
                    pretty.append(c);
                    if (!inString) {
                        pretty.append('\n').append("  ".repeat(indent));
                    }
                    break;
                case ':':
                    pretty.append(c);
                    if (!inString) {
                        pretty.append(' ');
                    }
                    break;
                case '"':
                    pretty.append(c);
                    inString = !inString;
                    break;
                default:
                    pretty.append(c);
            }
        }

        return pretty.toString();
    }
}