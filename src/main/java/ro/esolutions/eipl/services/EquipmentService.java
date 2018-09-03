package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.EquipmentRepository;
import ro.esolutions.eipl.types.MabecCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    @NonNull
    private final EquipmentRepository equipmentRepository;

    public List<EquipmentModel> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public EquipmentModel addNewEquipment(final EquipmentModel equipmentModel) {
        return EquipmentMapper.fromEntityToModel(equipmentRepository.save(EquipmentMapper.fromModelToEntity(equipmentModel)));
    }

    public List<Equipment> uploadEquipmentFromCSV(final MultipartFile file) {
        List<Equipment> equipmentList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            for(CSVRecord csvRecord : csvParser) {
                Equipment equipment = new Equipment(null, csvRecord.get(0), csvRecord.get(1),
                        MabecCode.valueOf(csvRecord.get(2)), csvRecord.get(3), csvRecord.get(4), csvRecord.get(5));
                Optional<Equipment> equipmentOptional = equipmentRepository.findByCode(equipment.getCode());
                if (equipmentOptional.isPresent()) {
                    equipment.setId(equipmentOptional.get().getId());
                    equipmentList.add(equipment);
                } else {
                    equipmentList.add(equipment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        equipmentRepository.saveAll(equipmentList);
        return equipmentList;
    }
}