package africa.semicolon.backendemployeemanagementsystem.repository;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findById(String id);

    Boolean existsByUserName(String userName);

    Boolean existsByEmailId(String emailId);

}
