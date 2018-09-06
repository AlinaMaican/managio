package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.exceptions.EquipmentUploadFileNotValid;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository;
import ro.esolutions.eipl.repositories.EmployeeRepository;
import ro.esolutions.eipl.repositories.EquipmentRepository;
import ro.esolutions.eipl.types.MabecCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EquipmentService {

    @NonNull
    private final EquipmentRepository equipmentRepository;
    @NonNull
    private final EmployeeEquipmentRepository employeeEquipmentRepository;
    @NonNull
    private final EmployeeRepository employeeRepository;

    public List<EquipmentModel> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public EquipmentModel addNewEquipment(final EquipmentModel equipmentModel) {
        return EquipmentMapper.fromEntityToModel(equipmentRepository.save(EquipmentMapper.fromModelToEntity(equipmentModel)));
    }

    public void uploadEquipmentFromCSV(final MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            List<Equipment> equipmentsToSave = StreamSupport.stream(csvParser.spliterator(), false)
                    .filter(record -> MabecCode.contains(record.get(2)))
                    .map(record -> {
                       Equipment equipment = EquipmentMapper.fromRecordToEntity(record);
                        equipmentRepository.findByCode(equipment.getCode())
                                .ifPresent(equipment1 -> equipment.setId(equipment1.getId()));
                        return equipment;
                    }).collect(Collectors.toList());
            equipmentRepository.saveAll(equipmentsToSave);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new EquipmentUploadFileNotValid();
        }
    }

    public List<EquipmentModel> getAllAvailableEquipments() {
        return equipmentRepository.findAllByIsAvailable(true)
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EquipmentModel> getFilteredEquipments(String searchValue) {
        List<EquipmentModel> resultEquipments = equipmentRepository.findDistinctByNameContainingIgnoreCase(searchValue)
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
        return resultEquipments;
    }

}

//    public void saveAllocatedEquipments(List<EmployeeEquipmentModel> allocatedEquipments) {
//        allocatedEquipments.stream().forEach(employeeEquipmentModel -> {
//            EquipmentModel eq = EquipmentMapper.fromEntityToModel(equipmentRepository.findById(employeeEquipmentModel.getId()).get());
//            employeeEquipmentModel.setEquipment(eq);
//        });
//        List<EmployeeEquipment> listToSave = allocatedEquipments.stream().map(equipmentModel -> {
//            EmployeeEquipment employeeEquipment = EmployeeEquipmentMapper.fromModelToEntity(equipmentModel);
//            employeeEquipment.setEmployee(allocatedEquipments);
//            employeeEquipment.getEquipment().setIsAvailable(false);
//            return employeeEquipment;
//        }).collect(Collectors.toList());
//        employeeEquipmentRepository.saveAll(listToSave);
//    }
}