package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;

import java.util.List;


public interface EmployeeService {

    CreateEmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);

    List<Employee> getAllEmployees();

    Employee findByEmployeeId(String id);

    Employee updateEmployee(String id, Employee employeeDetails);

}
