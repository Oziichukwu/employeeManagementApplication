package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        if (employeeRepository.existsByUserName(createEmployeeRequest.getUserName())) {
            throw new RunTimeExceptionPlaceholder("username already exist");
        }

        if (employeeRepository.existsByEmailId(createEmployeeRequest.getEmail())) {
            throw new DuplicateEmailException("Email already exist");
        }

        Employee employee1 = Employee.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .emailId(createEmployeeRequest.getEmail())
                .userName(createEmployeeRequest.getUserName())
                .build();

        employeeRepository.save(employee1);

        return CreateEmployeeResponse.builder()
                .userName(employee1.getUserName())
                .id(employee1.getId())
                .build();
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }
}
