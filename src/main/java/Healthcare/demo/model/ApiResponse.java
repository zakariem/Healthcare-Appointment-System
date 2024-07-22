package Healthcare.demo.model;

public class ApiResponse {

    private String message;
    private boolean success;

    // Default constructor for JSON deserialization
    public ApiResponse() {
    }

    // Constructor for initializing fields
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for success
    public boolean isSuccess() {
        return success;
    }

    // Setter for success
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
