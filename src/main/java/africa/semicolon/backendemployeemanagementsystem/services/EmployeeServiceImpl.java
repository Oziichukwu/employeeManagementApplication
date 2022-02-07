package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequestDto;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponseDto;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.EmployeeNotFoundException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto createEmployeeRequest) {

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

                saveEmployee(employee1);

        return CreateEmployeeResponseDto.builder()
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

    private Employee saveEmployee(Employee employee){
        if (employee == null){
            throw new EmployeeNotFoundException("Employee cannot be null");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeDetails(String employeeId, JsonPatch patch) {

        Optional<Employee> employeeQuery = employeeRepository.findById(employeeId);

        if (employeeQuery.isEmpty()){
            throw new EmployeeNotFoundException("Employee with id" + employeeId + "does not exist");
        }

        Employee updatedEmployee = employeeQuery.get();

        try {
            updatedEmployee = implementPatchOnEmployee(patch , updatedEmployee);
            return saveEmployee(updatedEmployee);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new EmployeeNotFoundException("Employee update Failed");
        }
    }

    private Employee implementPatchOnEmployee(JsonPatch patch, Employee employeeToBeUpdated) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patch.apply(objectMapper.convertValue(employeeToBeUpdated, JsonNode.class));

        return objectMapper.treeToValue(patched , Employee.class);
    }

}
