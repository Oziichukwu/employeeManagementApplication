package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        if (employeeRepository.existsByUserName(createEmployeeRequest.getUserName())){
            throw new RunTimeExceptionPlaceholder("username already exist");
        }

        if (employeeRepository.existsByEmail(createEmployeeRequest.getEmail())){
            throw new DuplicateEmailException("Email already exist");
        }

        Employee employee1 = Employee.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .email(createEmployeeRequest.getEmail())
                .build();

        return CreateEmployeeResponse.builder()
                .userName(employee1.getUserName())
                .id(employee1.getId())
                .build();
    }
}
