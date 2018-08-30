package ro.esolutions.eipl.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.repositories.EquipmentRepository;
import ro.esolutions.eipl.types.MabecCode;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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
