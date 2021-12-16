package africa.semicolon.backendemployeemanagementsystem;

import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
import africa.semicolon.backendemployeemanagementsystem.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndEmployeeManagementSystemApplication  implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BackEndEmployeeManagementSystemApplication.class, args);
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee employee1 = Employee.builder()
                .firstName("uchechukwu")
                .lastName("micheal")
                .email("ukaegbu13@gmail.com")
                .userName("uchebest")
                .build();

        employeeRepository.save(employee1);


        Employee employee2 = Employee.builder()
                .firstName("uche")
                .lastName("mike")
                .email("uche13@gmail.com")
                .userName("mikey")
                .build();

        employeeRepository.save(employee2);
    }
}
