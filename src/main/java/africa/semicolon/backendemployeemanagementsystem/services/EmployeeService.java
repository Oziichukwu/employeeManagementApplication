package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    String createEmployee(CreateEmployeeRequest createEmployeeRequest);

    List<Employee> getAllEmployees();


}
