package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.Equipment;

public interface EquipmentsRepository extends JpaRepository<Equipment,Long> {
}
