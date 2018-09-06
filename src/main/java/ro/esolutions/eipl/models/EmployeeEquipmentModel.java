package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEquipmentModel {

    private Long id;

    @NotNull
    private EmployeeModel employee;

    @NotNull
    private EquipmentModel equipment;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
