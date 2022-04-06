package africa.semicolon.backendemployeemanagementsystem.web.controller;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequestDto;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.ApiResponse;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponseDto;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.EmployeeNotFoundException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import africa.semicolon.backendemployeemanagementsystem.services.EmployeeService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequestDto createEmployeeRequest) {

        try {
               CreateEmployeeResponseDto responseDto =  employeeService.createEmployee(createEmployeeRequest);
                return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (DuplicateEmailException | RunTimeExceptionPlaceholder e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findByEmployeeId(@PathVariable Long id){
        try{
            return new ResponseEntity<>(employeeService.findByEmployeeId(id), HttpStatus.OK);
        }catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
      try {
          return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDetails), HttpStatus.OK);
      }catch (EmployeeNotFoundException e){
          return new ResponseEntity<>(new ApiResponse(false, "Employee Update Failed"), HttpStatus.BAD_REQUEST);
      }
    }


     @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteEmployee(@PathVariable Long id){

        try {
             employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(new ApiResponse(true, "Employee Deleted Successfully"), HttpStatus.NO_CONTENT);
        }catch(EmployeeNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping(path = "/{employeeId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateEmployeeDetails(@PathVariable Long employeeId, @RequestBody JsonPatch patch){

        try {
            Employee updatedEmployee = employeeService.updateEmployeeDetails(employeeId, patch);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        }catch (EmployeeNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


/*
* presentation layer
* business logic
* data access loose coupling
* */