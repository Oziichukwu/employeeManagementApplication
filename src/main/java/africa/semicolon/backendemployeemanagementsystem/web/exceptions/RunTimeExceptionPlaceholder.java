package africa.semicolon.backendemployeemanagementsystem.web.exceptions;

public class RunTimeExceptionPlaceholder extends EmployeeManagementException {
    public RunTimeExceptionPlaceholder() {
    }

    public RunTimeExceptionPlaceholder(String message) {
        super(message);
    }

    public RunTimeExceptionPlaceholder(String message, Throwable cause) {
        super(message, cause);
    }

    public RunTimeExceptionPlaceholder(Throwable cause) {
        super(cause);
    }
}
