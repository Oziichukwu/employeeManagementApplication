package africa.semicolon.backendemployeemanagementsystem.repository;

import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(String userId);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);

}
