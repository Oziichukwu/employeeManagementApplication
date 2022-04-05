package africa.semicolon.backendemployeemanagementsystem.services;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.request.CreateEmployeeRequestDto;
import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponseDto;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.email.EmailSender;
import africa.semicolon.backendemployeemanagementsystem.services.cloud.CloudService;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.DuplicateEmailException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.EmployeeNotFoundException;
import africa.semicolon.backendemployeemanagementsystem.web.exceptions.RunTimeExceptionPlaceholder;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private CloudService cloudService;

    @Autowired
    private EmailSender emailSender;

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
                .generateEmployeeId(generateEmployeeId())
                .build();

        try{
            if (createEmployeeRequest.getImage() != null){
                Map<?,?> uploadImageResult = cloudService.upload(createEmployeeRequest.getImage().getBytes(),
                        ObjectUtils.asMap("public_id",
                                "employee/" + createEmployeeRequest.getImage().getOriginalFilename(),
                                "overwrite", true
                        ));
            employee1.setImageUrl(uploadImageResult.get("url").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
                saveEmployee(employee1);


        emailSender.send("oziichukwu1@gmail.com", "Employee Management System", "Welcome to XYZ firm"
            + "\n" + createEmployeeRequest.getLastName() + " Has been saved successfully"
        );


        return CreateEmployeeResponseDto.builder()
                .userName(employee1.getUserName())
                .id(employee1.getId())
                .generatedEmployeeId(generateEmployeeId())
                .build();

    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmployeeId(Long id) {

         Optional<Employee> userEmployee = employeeRepository.findById(id);
        return userEmployee.orElseThrow(()->
                new EmployeeNotFoundException("Employee does not exist"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {

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
    public Employee updateEmployeeDetails(Long employeeId, JsonPatch patch) {

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

    @Override
    public void deleteEmployeeById(Long employeeId) {

        Employee employeeToDelete = findByEmployeeId(employeeId);

        deleteEmployee(employeeToDelete);
    }

    private void deleteEmployee(Employee employeeToDelete) {

        employeeRepository.delete(employeeToDelete);
    }


    private Employee implementPatchOnEmployee(JsonPatch patch, Employee employeeToBeUpdated) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patch.apply(objectMapper.convertValue(employeeToBeUpdated, JsonNode.class));

        return objectMapper.treeToValue(patched , Employee.class);
    }

    private String generateEmployeeId(){
        String employeeId =  UUID.randomUUID().toString();
        log.info("employee id is -> {}", employeeId);
        return employeeId;
    }

}
