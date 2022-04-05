//package africa.semicolon.backendemployeemanagementsystem.web.controller;
//
//
//import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
//import africa.semicolon.backendemployeemanagementsystem.repository.EmployeeRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Slf4j
//@SpringBootTest
//@AutoConfigureMockMvc
//@Sql(scripts = {"/db/insert.sql"})
//public class EmployeeControllerTest {
//
//    private String createJsonObject;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() throws JsonProcessingException {
//
//        Employee employee = new Employee();
//        employee.setFirstName("balikis");
//        employee.setLastName("raska");
//        employee.setEmailId("balikis@gmail.com");
//        employee.setUserName("raska567");
//        createJsonObject = objectMapper.writeValueAsString(employee);
//    }
//
//    @Test
//    @DisplayName("Get All Employee api test")
//    void getAllEmployeesTest() throws Exception {
//
//        mockMvc.perform(get("/api/v1/employee")
//                .contentType("application/json"))
//                .andExpect(status().is(200))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("Create Employee api Test")
//    void createEmployeeApiTest() throws Exception {
//        mockMvc.perform(post("/api/v1/employee/create_employee")
//                .contentType("application/json")
//                .content(createJsonObject))
//                .andExpect(status().is(200))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("Update Employee api Test")
//    void updateEmployeeApiTest() throws Exception {
//
//        Employee employee = employeeRepository.findById("35").orElse(null);
//        assertThat(employee).isNotNull();
//
//        mockMvc.perform(patch("/api/v1/employee/35")
//                .contentType("application/json-patch+json")
//                .content(Files.readAllBytes(Path.of("payload.json"))))
//                .andExpect(status().is(200))
//                .andDo(print());
//
//
//        employee = employeeRepository.findById("35").orElse(null);
//        assertThat(employee).isNotNull();
//        assertThat(employee.getFirstName()).isEqualTo("delle");
//
//
//    }
//
//
//}
