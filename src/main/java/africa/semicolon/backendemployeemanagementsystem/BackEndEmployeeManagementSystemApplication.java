package africa.semicolon.backendemployeemanagementsystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackEndEmployeeManagementSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackEndEmployeeManagementSystemApplication.class, args);
    }

//    @Autowired
//    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

//        Employee employee1 = Employee.builder()
//                .firstName("uche")
//                .lastName("fatia")
//                .emailId("ukaegb673@gmail.com")
//                .userName("ubest")
//                .build();
//
//        employeeRepository.save(employee1);
//
//
//        Employee employee2 = Employee.builder()
//                .firstName("uhje")
//                .lastName("mike")
//                .emailId("uche13@gmail.com")
//                .userName("mikey")
//                .build();
//
//        employeeRepository.save(employee2);
    }
}
