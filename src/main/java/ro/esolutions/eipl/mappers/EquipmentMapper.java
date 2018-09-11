package ro.esolutions.eipl.mappers;

import org.apache.commons.csv.CSVRecord;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.types.MabecCode;


public final class EquipmentMapper {
    private EquipmentMapper() {
    }

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

    public static Equipment fromModelToEntity(final EquipmentModel equipmentModel) {
        return Equipment.builder()
                .id(equipmentModel.getId())
                .name(equipmentModel.getName())
                .code(equipmentModel.getCode())
                .mabecCode(equipmentModel.getMabecCode())
                .protectionType(equipmentModel.getProtectionType())
                .size(equipmentModel.getSize())
                .sex(equipmentModel.getSex())
                .isAvailable(true)
                .build();
    }

    public static Equipment fromRecordToEntity(final CSVRecord csvRecord) {
        return Equipment.builder()
                .id(null)
                .name(csvRecord.get(0))
                .code(csvRecord.get(1))
                .mabecCode(MabecCode.valueOf(csvRecord.get(2)))
                .protectionType(csvRecord.get(3))
                .size(csvRecord.get(4))
                .sex(csvRecord.get(5))
                .isAvailable(true)
                .build();
    }

    public static String fromModelToCsvString(EquipmentModel equipmentModel) {
        return equipmentModel.getName() + "," +
                equipmentModel.getCode() + "," +
                equipmentModel.getMabecCode().toString() + "," +
                equipmentModel.getProtectionType() + "," +
                equipmentModel.getSize() + "," +
                equipmentModel.getSex();
    }
}