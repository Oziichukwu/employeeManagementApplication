package africa.semicolon.backendemployeemanagementsystem.data.dtos.request;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CreateEmployeeRequestDto {

    private String firstName;

    private String lastName;

    private String emailId;

    private String userName;

    private MultipartFile image;
}
