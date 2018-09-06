package ro.esolutions.eipl.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "EMPLOYEES_GEN_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EMPLOYEES_GEN_SEQ", sequenceName = "employee_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String workingStation;

    private String helmetSize;

    private String clothingSize;

    private String footwearSize;

}
