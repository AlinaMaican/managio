package ro.esolutions.eipl.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private Employee employee;

    @ManyToOne
    private Equipment equipment;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @PrePersist
    public void initStartDate() {
        if (this.startDate == null) {
            this.startDate = LocalDate.now();
        }
    }

}
