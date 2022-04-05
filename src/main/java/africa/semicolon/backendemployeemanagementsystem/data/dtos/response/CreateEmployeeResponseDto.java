package africa.semicolon.backendemployeemanagementsystem.data.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponseDto {

    private Long id;

    private String userName;

    private String generatedEmployeeId;

}
