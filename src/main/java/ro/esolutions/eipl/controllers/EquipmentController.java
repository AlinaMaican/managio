package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.services.EquipmentService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private static final String BINDING_RESULT_ERROR_MESSAGE = "Equipment not valid";
    public static final String JSON_EMPTY_BODY = "{}";

    @NonNull
    private final EquipmentService equipmentService;


    @GetMapping("/all")
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @PostMapping
    public ResponseEntity<Object> addNewEquipment(@RequestBody @Valid EquipmentModel equipmentModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", BINDING_RESULT_ERROR_MESSAGE));
        }
        equipmentModel.setId(null);
        return ResponseEntity.ok(equipmentService.addNewEquipment(equipmentModel));
    }

    @PostMapping(value = "/importByFile")
    public ResponseEntity<Object> uploadEquipmentFromCSV(@RequestPart("file") final MultipartFile file) {
        equipmentService.uploadEquipmentFromCSV(file);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }

    @GetMapping("/available")
    public ResponseEntity<List<EquipmentModel>> getAllAvailableEquipments() {
        return ResponseEntity.ok(equipmentService.getAllAvailableEquipments());
    }

    @PostMapping("/saveAllocatedEquipments")
    public ResponseEntity<Object> saveAllocatedEquipments(@RequestBody List<EmployeeEquipmentModel> allocatedEquipments) {
        equipmentService.saveAllocatedEquipments(allocatedEquipments);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }
}