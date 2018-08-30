package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.services.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;


    @GetMapping("/all")
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @PostMapping(value = "/file")
    public ResponseEntity<Object> uploadEquipmentFromCSV(@RequestPart("file") final MultipartFile fileToBeUploaded){
        return ResponseEntity.ok(equipmentService.uploadEquipmentFromCSV(fileToBeUploaded));
    }
}
