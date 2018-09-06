package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Equipment;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

     Optional<Equipment> findByCode(String code);

     List<Equipment> findAllByIsAvailable(boolean b);

    List<Equipment> findDistinctByNameContainingIgnoreCase(String searchValue);
}
