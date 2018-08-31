package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.EmployeeEquipment

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EmployeeEquipmentGenerator {
    static aEmployeeEquipment(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : aEmployeeModel(),
                      equipment: anEquipmentModel()]
        values << overrides
        return EmployeeEquipment.newInstance(values)
    }
}
