package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EmployeeEquipmentModel

class EmployeeEquipmentModelGenerator {

    static aEmployeeEquipmentModel(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : [id: 0L],
                      equipment: [id: 0L],]
        values << overrides
        return EmployeeEquipmentModel.newInstance(values)
    }
}
