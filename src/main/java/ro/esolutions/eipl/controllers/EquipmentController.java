package ro.esolutions.eipl.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.services.EquipmentService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = Objects.requireNonNull(equipmentService, "EquipmentService must not be null");
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }
}