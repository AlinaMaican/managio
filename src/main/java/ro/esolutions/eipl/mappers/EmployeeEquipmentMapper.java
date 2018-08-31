package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;

public final class EmployeeEquipmentMapper {
    private EmployeeEquipmentMapper(){
    }


    public static EmployeeEquipmentModel fromEntityToModel(final EmployeeEquipment employeeEquipment) {
        return EmployeeEquipmentModel.builder()
                .id(employeeEquipment.getId())
                .employee(employeeEquipment.getEmployee())
                .equipment(employeeEquipment.getEquipment())
                .build();
    }

    public static EmployeeEquipment fromModelToEntity(final EmployeeEquipmentModel employeeEquipmentModel) {
        return EmployeeEquipment.builder()
                .id(employeeEquipmentModel.getId())
                .employee(employeeEquipmentModel.getEmployee())
                .equipment(employeeEquipmentModel.getEquipment())
                .build();
    }}
