package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Equipment;

@Repository
public interface EquipmentsRepository extends JpaRepository<Equipment,Long> {
}
