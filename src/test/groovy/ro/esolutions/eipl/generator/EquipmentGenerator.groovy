package ro.esolutions.eipl.generator

import ro.esolutions.eipl.entities.Equipment

class EquipmentGenerator {

    static aEquipment(Map overrides = [:]) {
        Map values = [id : 0L,
                      name : "name",
                      code: "code",
                      mabecCode : "MABEC_01",
                      protectionType: "de cap",
                      size:"M",
                      sex :"F"]
        values << overrides
        return Equipment.newInstance(values)
    }
}

