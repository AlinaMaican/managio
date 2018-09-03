package ro.esolutions.eipl.generator

import ro.esolutions.eipl.models.EquipmentModel

class EquipmentModelGenerator {

    static aEquipmentModel(Map overrides = [:]) {
        Map values = [id : 0L,
                      name : "name",
                      code: "code",
                      mabecCode : "MABEC_01",
                      protectionType: "de cap",
                      size:"M",
                      sex :"F"]
        values << overrides
        return EquipmentModel.newInstance(values)
    }
}