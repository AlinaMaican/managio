package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.services.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    @NonNull
    private final EquipmentService equipmentService;

    @GetMapping("/all")
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }
}