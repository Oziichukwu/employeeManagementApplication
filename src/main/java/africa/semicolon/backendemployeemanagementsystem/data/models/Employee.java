package africa.semicolon.backendemployeemanagementsystem.data.models;


import lombok.*;

import javax.persistence.*;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")

    private String lastName;

    @Column(name = "email")

    private String email;

    @Column(name = "user_name")
    private String userName;
}
