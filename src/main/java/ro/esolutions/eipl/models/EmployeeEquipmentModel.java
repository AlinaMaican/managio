package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.entities.Equipment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEquipmentModel {

    private Long id;

    private Employee employee;

    private Equipment equipment;
}
