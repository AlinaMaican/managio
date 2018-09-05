package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;

public class EmployeeEquipmentReportMapper {

    private EmployeeEquipmentReportMapper(){
    }

    public static EmployeeEquipmentReportModel fromEntityToModel(final EmployeeEquipment employeeEquipment) {
        return EmployeeEquipmentReportModel.builder()
                .firstName(employeeEquipment.getEmployee().getFirstName())
                .lastName(employeeEquipment.getEmployee().getLastName())
                .equipmentName(employeeEquipment.getEquipment().getName())
                .equipmentCode(employeeEquipment.getEquipment().getCode())
                .protectionType(employeeEquipment.getEquipment().getProtectionType())
                .size(employeeEquipment.getEquipment().getSize())
                .endDate(employeeEquipment.getEndDate())
                .build();
    }
}
