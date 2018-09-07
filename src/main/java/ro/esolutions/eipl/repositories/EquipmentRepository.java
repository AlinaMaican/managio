package ro.esolutions.eipl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Equipment;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {


    Optional<Equipment> findByCode(String code);

    Page<Equipment> findAllByOrderByIdAsc(Pageable pageable);

    List<Equipment> findAllByIsAvailable(boolean b);

    List<Equipment> findDistinctByNameContainingIgnoreCase(String searchValue);

    @Query("select e from Equipment e where e.id in ?1")
    List<Equipment> findByIds(Collection<Long> ids);

}
