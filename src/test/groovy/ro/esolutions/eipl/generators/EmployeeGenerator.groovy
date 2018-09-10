package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.Employee

class EmployeeGenerator {

    static aEmployee(Map overrides = [:]) {
        Map values = [id : 0L,
                      firstName: "firstName",
                      lastName : "lastName",
                      workingStation : "workingStation",
                      helmetSize : "helmetSize",
                      clothingSize : "clothingSize",
                      footwearSize : "footwearSize"]
        values << overrides
        return Employee.newInstance(values)
    }
}
