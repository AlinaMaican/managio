package ro.esolutions.eipl.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees_equipments")
public class EmployeeEquipment {

    @Id
    @GeneratedValue(generator = "EMPLOYEES_EQUIPMENTS_GEN_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EMPLOYEES_EQUIPMENTS_GEN_SEQ", sequenceName = "employee_equipment_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @NotNull
    private Employee employee;

    @ManyToOne
    @NotNull
    private Equipment equipment;
}
