//package africa.semicolon.backendemployeemanagementsystem.data.repository;
//
//
//import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
//import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@Slf4j
//@SpringBootTest
////@Sql(scripts = {"/db/insert.sql"})
//public class EmployeeRepositoryTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//
//    @Test
//    @DisplayName("Save an Employee to a database")
//    void saveEmployeeToDatabaseTest(){
//
//        //Given
//        Employee employee = new Employee();
//        employee.setFirstName("shola");
//        employee.setLastName("jola");
//        employee.setUserName("sholastical");
//        employee.setEmailId("jola@gmail.com");
//        employee.setGenerateEmployeeId(generateEmployeeId());
//        assertThat(employee.getId()).isNull();
//
//        //when
//        employeeRepository.save(employee);
//
//        assertThat(employee).isNotNull();
//        assertThat(employee.getGenerateEmployeeId()).isNotNull();
//
//    }
//
//    @Test
//    @DisplayName("Already existing employee in the database can be located")
//    void findExistingEmployeesInTheDatabase(){
//
//        Employee employee = employeeRepository.findById("33").orElse(null);
//
//        assertThat(employee).isNotNull();
//        assertThat(employee.getId()).isEqualTo("33");
//        assertThat(employee.getFirstName()).isEqualTo("seyi");
//        assertThat(employee.getLastName()).isEqualTo("micheal");
//        assertThat(employee.getEmailId()).isEqualTo("seyi@gmail.com");
//        assertThat(employee.getUserName()).isEqualTo("seyi456");
//
//        log.info("Employee retrieved -> {}", employee);
//    }
//
//    @Test
//    @DisplayName("Find All Employees in the database")
//    void findAllEmployeesInTheDatabase(){
//        List<Employee> employees = employeeRepository.findAll();
//        assertThat(employees.size()).isEqualTo(4);
//        assertThat(employees).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Find employee by id")
//    void findEmployeeById(){
//        boolean employee = employeeRepository.existsByEmailId("bayi@gmail.com");
//
//        assertThat(employee).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Update product Test")
//    void CheckThatEmployeeCanBeUpdated(){
//
//        Employee employee = employeeRepository.findById("34").orElse(null);
//
//        assertThat(employee).isNotNull();
//        assertThat(employee.getFirstName()).isEqualTo("bayi");
//        assertThat(employee.getLastName()).isEqualTo("saviour");
//        assertThat(employee.getEmailId()).isEqualTo("bayi@gmail.com");
//
//        employee.setFirstName("rukayat");
//        employee.setLastName("shilla");
//        employee.setEmailId("shilla@gmail.com");
//
//        assertThat(employee.getFirstName()).isEqualTo("rukayat");
//        assertThat(employee.getLastName()).isEqualTo("shilla");
//        assertThat(employee.getEmailId()).isEqualTo("shilla@gmail.com");
//
//
//
//    }
//
//
//    private String generateEmployeeId(){
//        String employeeId =  UUID.randomUUID().toString();
//        log.info("employee id is -> {}", employeeId);
//        return employeeId;
//    }
//
//}
