package africa.semicolon.backendemployeemanagementsystem.data.dtos.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeRequest {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

}
