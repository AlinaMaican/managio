package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EmployeeModel

class EmployeeModelGenerator {

    static aEmployeeModel(Map overrides = [:]) {
        Map values = [id : 0L,
                      firstName: "firstName",
                      lastName : "lastName",
                      workingStation : "workingStation"]
        values << overrides
        return EmployeeModel.newInstance(values)
    }
}
