package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.models.EquipmentModel;

public class EquipmentMapper {

    private EquipmentMapper(){}

    public static EquipmentModel fromEntityToModel(final Equipment equipment) {
        return EquipmentModel.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .code(equipment.getCode())
                .mabecCode(equipment.getMabecCode())
                .protectionType(equipment.getProtectionType())
                .size(equipment.getSize())
                .sex(equipment.getSex())
                .build();
    }
}