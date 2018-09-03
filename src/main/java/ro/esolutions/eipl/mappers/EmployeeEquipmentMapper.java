package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;

public final class EmployeeEquipmentMapper {
    private EmployeeEquipmentMapper(){
    }


    public static EmployeeEquipmentModel fromEntityToModel(final EmployeeEquipment employeeEquipment) {
        return EmployeeEquipmentModel.builder()
                .id(employeeEquipment.getId())
                .employee(EmployeeMapper.fromEntityToModel(employeeEquipment.getEmployee()))
                .equipment(EquipmentMapper.fromEntityToModel(employeeEquipment.getEquipment()))
                .build();
    }

    public static EmployeeEquipment fromModelToEntity(final EmployeeEquipmentModel employeeEquipmentModel) {
        return EmployeeEquipment.builder()
                .id(employeeEquipmentModel.getId())
                .employee(EmployeeMapper.fromModelToEntity(employeeEquipmentModel.getEmployee()))
                .equipment(EquipmentMapper.fromModelToEntity(employeeEquipmentModel.getEquipment()))
                .build();
    }}
