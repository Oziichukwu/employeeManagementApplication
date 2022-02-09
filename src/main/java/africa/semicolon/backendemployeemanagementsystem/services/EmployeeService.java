package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequestDto;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponseDto;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;


public interface EmployeeService {

    CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto createEmployeeRequest);

    List<Employee> getAllEmployees();

    Employee findByEmployeeId(String id);

    Employee updateEmployee(String id, Employee employeeDetails);

    Employee updateEmployeeDetails(String employeeId, JsonPatch patch);

    void deleteEmployeeById(String employeeId);

}
