package africa.semicolon.backendemployeemanagementsystem.web.exceptions;

public class EmployeeManagementException extends RuntimeException {
    public EmployeeManagementException() {
    }

    public EmployeeManagementException(String message) {
        super(message);
    }

    public EmployeeManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeManagementException(Throwable cause) {
        super(cause);
    }
}
