package ro.esolutions.eipl.services;

import org.springframework.stereotype.Service;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.EquipmentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = Objects.requireNonNull(equipmentRepository, "EquipmentRepository must not be null");
    }

    public List<EquipmentModel> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }


    public EquipmentModel addNewEquipment(final EquipmentModel equipmentModel) {
        return EquipmentMapper.fromEntityToModel(equipmentRepository.save(EquipmentMapper.fromModelToEntity(equipmentModel)));
    }
}