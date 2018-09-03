package ro.esolutions.eipl.generator

import ro.esolutions.eipl.entities.Equipment
import ro.esolutions.eipl.types.MabecCode

class EquipmentFromCSV {
    static anCSVEquipment(Map overrides = [:]) {
        Map values = [
                id            : 1L,
                name          : "casca",
                code          : "code123",
                mabecCode     : MabecCode.MABEC_01,
                protectionType: "cap",
                size          : "S",
                sex           : "F"]
        values << overrides
        return Equipment.newInstance(values)
    }
}
