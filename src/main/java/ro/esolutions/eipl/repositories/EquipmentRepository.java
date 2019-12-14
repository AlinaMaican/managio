package ro.esolutions.eipl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Equipment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByCode(String code);

    Equipment getEquipmentByCode(String code);

    Page<Equipment> getEquipmentByDateOfExpirationAfter(Pageable pageable, LocalDate localDate);

    List<Equipment> findAllByIsAvailableAndDateOfExpirationAfter(boolean b,LocalDate localDate);

    List<Equipment> findDistinctByNameContainingIgnoreCase(String searchValue);

    List<Equipment> findDistinctByNameContainingIgnoreCaseAndIsAvailable(String searchValue,Boolean value);

    @Query("SELECT eq FROM Equipment eq LEFT JOIN EmployeeEquipment ee on (eq.id = ee.equipment) where ee.id is null and eq.dateOfExpiration > current_date ")
    List<Equipment> getAllUnusedEquipments();

    @Query("SELECT eq FROM Equipment eq  where eq.dateOfExpiration < current_date ")
    Page<Equipment> getAllExpiredEquipments(Pageable pageable);

    @Query("SELECT eq FROM Equipment eq  where eq.dateOfExpiration < current_date ")
    List<Equipment> getAllExpiredEquipmentsAsList();


    List<Equipment> findByDateOfCreationBetween(LocalDate startDate,LocalDate endDate);

    List<Equipment> findByDateOfExpirationBetween(LocalDate startDate,LocalDate endDate);
}