package dto;

public class ResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode;

    public ResponseDTO() {}

    public static <T> ResponseDTO<T> success(T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ResponseDTO<T> error(String message) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseDTO<T> error(String message, String errorCode) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return response;
    }

    // Getters e Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean s) { this.success = s; }
    public String getMessage() { return message; }
    public void setMessage(String m) { this.message = m; }
    public T getData() { return data; }
    public void setData(T d) { this.data = d; }
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String e) { this.errorCode = e; }
}