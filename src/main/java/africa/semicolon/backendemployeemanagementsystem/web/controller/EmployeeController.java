package africa.semicolon.backendemployeemanagementsystem.web;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequestDto;
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
@RequestMapping("/api/v1/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/create_employee")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequestDto createEmployeeRequest) {

        try {
            return new ResponseEntity<>(employeeService.createEmployee(createEmployeeRequest), HttpStatus.OK);

        } catch (DuplicateEmailException | RunTimeExceptionPlaceholder e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/api/v1/employees")
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findByEmployeeId(@PathVariable String id){
        try{
            return new ResponseEntity<>(employeeService.findByEmployeeId(id), HttpStatus.OK);
        }catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails){
      try {
          return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDetails), HttpStatus.OK);
      }catch (EmployeeNotFoundException e){
          return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
      }
    }


     @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>deleteEmployee(@PathVariable String id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("employee does not exist"));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping(path = "/{employeeId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateEmployeeDetails(@PathVariable String employeeId, @RequestBody JsonPatch patch){

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
* data access                loose coupling
* */