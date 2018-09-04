package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.EquipmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    @NonNull
    private final EquipmentRepository equipmentRepository;
    @NonNull
    private final UserService userService;

    public List<EquipmentModel> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }


    public EquipmentModel addNewEquipment(final EquipmentModel equipmentModel) {
        return EquipmentMapper.fromEntityToModel(equipmentRepository.save(EquipmentMapper.fromModelToEntity(equipmentModel)));
    }

    public List<EquipmentModel> getAllEquipmentForEmployee(Long userId) {
        userService.findByIdOrThrow(userId);
        return equipmentRepository.getAllEquipmentByEmployeeId(userId).stream()
                .map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }
}