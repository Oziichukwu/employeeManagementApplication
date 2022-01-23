package africa.semicolon.backendemployeemanagementsystem.controller;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.EmployeeInfo;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.EmployeeNotFoundException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import africa.semicolon.backendemployeemanagementsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("api/v1/create_employee")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {

        try {
            return new ResponseEntity<>(employeeService.createEmployee(createEmployeeRequest), HttpStatus.OK);

        } catch (DuplicateEmailException | RunTimeExceptionPlaceholder e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<?>findByEmployeeId(@PathVariable String id){
        try{
            return new ResponseEntity<>(employeeService.findByEmployeeId(id), HttpStatus.OK);
        }catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails){
      try {
          return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDetails), HttpStatus.OK);
      }catch (EmployeeNotFoundException e){
          return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
      }
    }


     @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("employee does not exist"));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
