package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EquipmentModel
import ro.esolutions.eipl.types.MabecCode

class EquipmentModelGenerator {

    static anEquipmentModel(Map ovverrides = [:]) {
        Map values = [
                id            : 1L,
                name          : "casca",
                code          : "code123",
                mabecCode     : MabecCode.MABEC_01,
                protectionType: "cap",
                size          : "S",
                sex           : "F"]
        values << ovverrides
        return EquipmentModel.newInstance(values)
    }
}
