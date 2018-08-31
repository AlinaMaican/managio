package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.EmployeeEquipment

import static ro.esolutions.eipl.generators.EmployeeGenerator.aEmployee
import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment

class EmployeeEquipmentGenerator {
    static aEmployeeEquipment(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : aEmployee(),
                      equipment: anEquipment()]
        values << overrides
        return EmployeeEquipment.newInstance(values)
    }
}
