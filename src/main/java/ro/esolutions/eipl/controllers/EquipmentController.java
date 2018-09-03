package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
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

    @NonNull
    private final EquipmentService equipmentService;
    private static final String BINDING_RESULT_ERROR_MESSAGE = "Equipment not valid";


    @GetMapping("/all")
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @PostMapping
    public ResponseEntity<Object> addNewEquipment(@RequestBody @Valid EquipmentModel equipmentModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", BINDING_RESULT_ERROR_MESSAGE));
        }
        equipmentModel.setId(null);
        return ResponseEntity.ok(equipmentService.addNewEquipment(equipmentModel));
    }


    @PostMapping(value = "/file")
    public ResponseEntity<Object> uploadEquipmentFromCSV(@RequestPart("file") final MultipartFile file){
        return ResponseEntity.ok(equipmentService.uploadEquipmentFromCSV(file));
    }
}