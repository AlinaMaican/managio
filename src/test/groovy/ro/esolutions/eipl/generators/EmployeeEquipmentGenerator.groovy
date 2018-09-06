package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.EmployeeEquipment

import java.time.LocalDate

import static ro.esolutions.eipl.generators.EmployeeGenerator.aEmployee
import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment

class EmployeeEquipmentGenerator {
    static aEmployeeEquipment(Map overrides = [:]) {
        Map values = [id       : 0L,
                      employee : aEmployee(),
                      equipment: anEquipment(),
                      startDate: LocalDate.of(2018, 8, 4),
                      endDate  : LocalDate.of(2018, 9, 4)]
        values << overrides
        return EmployeeEquipment.newInstance(values)
    }
}
