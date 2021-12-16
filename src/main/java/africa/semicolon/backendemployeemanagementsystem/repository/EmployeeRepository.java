package africa.semicolon.backendemployeemanagementsystem.repository;

import africa.semicolon.backendemployeemanagementsystem.data.dtos.response.CreateEmployeeResponse;
import africa.semicolon.backendemployeemanagementsystem.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,String> {

    //Optional<Employee> findByEmployeeId(String id);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);

}
