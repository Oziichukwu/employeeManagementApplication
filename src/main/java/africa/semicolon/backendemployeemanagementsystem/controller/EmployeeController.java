package africa.semicolon.backendemployeemanagementsystem.controller;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/api/v1/create_employee")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){

        try {
            return new ResponseEntity<>(employeeService.createEmployee(createEmployeeRequest), HttpStatus.OK);

        } catch (DuplicateEmailException | RunTimeExceptionPlaceholder e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
