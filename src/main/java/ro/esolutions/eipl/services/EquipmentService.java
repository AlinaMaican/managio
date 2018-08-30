package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.repositories.EquipmentRepository;
import ro.esolutions.eipl.types.MabecCode;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.models.EquipmentModel;

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

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<EquipmentModel> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<Equipment> uploadEquipmentFromCSV(final MultipartFile file){
        List<Equipment> equipmentList = new ArrayList<>();
        String line;
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = bufferedReader.readLine()) != null){
                String[] attributes = line.split(",");
                Equipment equipment = createEquipmentEntity(attributes);
                Optional<Equipment> equipmentOptional = equipmentRepository.findByCode(equipment.getCode());
                if(equipmentOptional.isPresent()){
                    equipment.setId(equipmentOptional.get().getId());
                    equipmentList.add(equipment);
                } else{
                    equipmentList.add(equipment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        equipmentList.stream().forEach(equipmentRepository::save);
        return equipmentList;
    }

    public Equipment createEquipmentEntity(final String[] attributes){
        String name = attributes[0];
        String code = attributes[1];
        MabecCode mabecCode = MabecCode.valueOf(attributes[2]);
        String protectionType = attributes[3];
        String size = attributes[4];
        String sex = attributes[5];

        return new Equipment(null, name, code, mabecCode, protectionType, size, sex);
    }
}
