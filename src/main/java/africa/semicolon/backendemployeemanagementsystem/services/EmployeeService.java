package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import org.springframework.stereotype.Service;


public interface EmployeeService {


    CreateEmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);


}
