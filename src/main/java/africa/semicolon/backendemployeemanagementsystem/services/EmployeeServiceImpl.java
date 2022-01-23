package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.EmployeeNotFoundException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        if (employeeRepository.existsByUserName(createEmployeeRequest.getUserName())) {
            throw new RunTimeExceptionPlaceholder("username already exist");
        }

        if (employeeRepository.existsByEmailId(createEmployeeRequest.getEmailId())) {
            throw new DuplicateEmailException("Email already exist");
        }

        Employee employee1 = Employee.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .emailId(createEmployeeRequest.getEmailId())
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

    @Override
    public Employee findByEmployeeId(String id) {

         Optional<Employee> userEmployee = employeeRepository.findById(id);
        return userEmployee.orElseThrow(()->
                new EmployeeNotFoundException("Employee does not exist"));
    }

    @Override
    public Employee updateEmployee(String id, Employee employeeDetails) {

        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("Employee does not exist"));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        updateEmployee.setUserName(employeeDetails.getUserName());

        return employeeRepository.save(updateEmployee);
    }

}
