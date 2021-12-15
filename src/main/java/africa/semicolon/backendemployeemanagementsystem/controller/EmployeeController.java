package africa.semicolon.backendemployeemanagementsystem.controller;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequest;
import africa.semicolon.backendemployeemanagementsystem.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @RequestMapping("/api/v1/create_employee")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){

        try {
            return new ResponseEntity<>(employeeService.createEmployee(createEmployeeRequest), HttpStatus.OK);

        } catch (DuplicateEmailException | RunTimeExceptionPlaceholder e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
