package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.eipl.types.ProtectionType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEquipmentReportModel {

    private String lastName;
    private String firstName;
    private String equipmentName;
    private String equipmentCode;
    private ProtectionType protectionType;
    private String size;
    private LocalDate endDate;
}
