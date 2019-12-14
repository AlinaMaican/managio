package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ro.esolutions.eipl.mappers.EquipmentMapper.fromEntityToModel;

@Service
@Transactional
@RequiredArgsConstructor
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
        return fromEntityToModel(equipmentRepository.save(EquipmentMapper.fromModelToEntity(equipmentModel)));
    }

    public Page<EquipmentModel> getAllEquipments(Pageable pageable) {
        return equipmentRepository.getEquipmentByDateOfExpirationAfter(pageable, LocalDate.now()).map(EquipmentMapper::fromEntityToModel);
    }

    public List<EquipmentModel> getLastAddedEquipments() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        return equipmentRepository.findByDateOfCreationBetween(startDate, endDate).stream().map(EquipmentMapper::fromEntityToModel).collect(Collectors.toList());
    }

    public List<EquipmentModel> getEquipmentsAboutToExpireThisWeek() {
        LocalDate current = LocalDate.now();
        LocalDate inAWeek = LocalDate.now().plusWeeks(1);
        return equipmentRepository.findByDateOfExpirationBetween(current, inAWeek).stream().map(EquipmentMapper::fromEntityToModel).collect(Collectors.toList());
    }

    public void uploadEquipmentFromCSV(final MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        CSVParser csvParser = null;
        try {
            csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Equipment> equipmentsToSave = StreamSupport.stream(csvParser.spliterator(), false)
                .filter(record -> MabecCode.contains(record.get(2)))
                .map(record -> {
                    Equipment equipment = EquipmentMapper.fromRecordToEntity(record);
                    return equipment;
                }).collect(Collectors.toList());
        equipmentRepository.saveAll(equipmentsToSave);
    }

    public List<EquipmentModel> getAllAvailableEquipments() {
        return equipmentRepository.findAllByIsAvailableAndDateOfExpirationAfter(true, LocalDate.now())
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public Page<EquipmentModel> getAllExpiredEquipments(Pageable pageable) {
        return equipmentRepository.getAllExpiredEquipments(pageable).map(EquipmentMapper::fromEntityToModel);
    }

    public List<EquipmentModel> getFilteredEquipments(String searchValue) {
        List<EquipmentModel> resultEquipments = equipmentRepository.findDistinctByNameContainingIgnoreCase(searchValue)
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
        return resultEquipments;
    }

    public List<EquipmentModel> getFilteredAvailableEquipments(String searchValue) {
        List<EquipmentModel> resultEquipments = equipmentRepository.findDistinctByNameContainingIgnoreCaseAndIsAvailable(searchValue, true)
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
        return resultEquipments;
    }

    public List<EquipmentModel> getAllUnusedEquipments() {
        return equipmentRepository.getAllUnusedEquipments().stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public String getMessage(Exception e) {
        String c = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        String numberOnly = c.replaceAll("[^0-9]", "");
        Long id = equipmentRepository.getEquipmentByCode(numberOnly).getId();
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage().replace("\n", "").replace("\r", "").replace("\"", "").replace(".", "");
        return message + " on the equipment with id: " + id;
    }

    //                        equipmentRepository.findByCode(equipment.getCode())
//                                .ifPresent(equipment1 -> equipment.setId(equipment1.getId()));
}