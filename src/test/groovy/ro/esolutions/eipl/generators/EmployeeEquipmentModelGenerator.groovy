package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EmployeeEquipmentModel

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EmployeeEquipmentModelGenerator {

    static aEmployeeEquipmentModel(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : aEmployeeModel().id,
                      equipment: anEquipmentModel().id]
        values << overrides
        return EmployeeEquipmentModel.newInstance(values)
    }
}
