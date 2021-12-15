package africa.semicolon.backendemployeemanagementsystem.data.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponse {

    private long id;

    private String userName;

}
