package africa.semicolon.backendemployeemanagementsystem.repository;

import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findById(Long id);

    Boolean existsByUserName(String userName);

    Boolean existsByEmailId(String emailId);

}
