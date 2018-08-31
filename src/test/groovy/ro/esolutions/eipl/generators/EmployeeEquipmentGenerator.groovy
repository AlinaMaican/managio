package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.EmployeeEquipment

class EmployeeEquipmentGenerator {
    static aEmployeeEquipment(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : [id: 0L],
                      equipment: [id: 0L],]
        values << overrides
        return EmployeeEquipment.newInstance(values)
    }
}
