package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.models.EmployeeModel;

public final class EmployeeMapper {
    private EmployeeMapper(){
    }

    public static EmployeeModel fromEntityToModel(final Employee employee) {
        return EmployeeModel.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .workingStation(employee.getWorkingStation())
                .build();
    }

    public static Employee fromModelToEntity(final EmployeeModel employeeModel) {
        return Employee.builder()
                .id(employeeModel.getId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .workingStation(employeeModel.getWorkingStation())
                .build();
    }
}

