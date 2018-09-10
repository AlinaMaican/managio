package ro.esolutions.eipl.mappers;

import org.apache.commons.csv.CSVRecord;
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
                .helmetSize(employee.getHelmetSize())
                .clothingSize(employee.getClothingSize())
                .footwearSize(employee.getFootwearSize())
                .build();
    }

    public static Employee fromModelToEntity(final EmployeeModel employeeModel) {
        return Employee.builder()
                .id(employeeModel.getId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .workingStation(employeeModel.getWorkingStation())
                .helmetSize(employeeModel.getHelmetSize())
                .clothingSize(employeeModel.getClothingSize())
                .footwearSize(employeeModel.getFootwearSize())
                .build();
    }

    public static Employee fromRecordToEntity(final CSVRecord csvRecord){
        return Employee.builder()
                .id(null)
                .firstName(csvRecord.get(0))
                .lastName(csvRecord.get(1))
                .workingStation(csvRecord.get(2))
                .build();
    }
}

