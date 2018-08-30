package ro.esolutions.eipl.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.esolutions.eipl.services.EquipmentService;

import java.io.File;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadEquipmentFromCSV(final File file){
        return ResponseEntity.ok(equipmentService.uploadEquipmentFromCSV(file));
    }
}
