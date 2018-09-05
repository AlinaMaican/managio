package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEquipmentReportModel {

    private String firstName;
    private String lastName;
    private String equipmentName;
    private String equipmentCode;
    private String protectionType;
    private String size;
    private LocalDate endDate;
}
