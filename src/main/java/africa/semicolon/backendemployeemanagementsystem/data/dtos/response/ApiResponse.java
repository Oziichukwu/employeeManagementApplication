package africa.semicolon.backendemployeemanagementsystem.data.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private boolean isSuccessful;

    private String message;

    private LocalDateTime responseDate;

    public ApiResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        responseDate = LocalDateTime.now();
    }
}
