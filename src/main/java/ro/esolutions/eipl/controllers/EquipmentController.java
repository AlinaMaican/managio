package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.services.EquipmentService;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private static final String BINDING_RESULT_ERROR_MESSAGE = "Equipment not valid";
    public static final String JSON_EMPTY_BODY = "{}";

    @NonNull
    private final EquipmentService equipmentService;

    @GetMapping("/all")
    public ResponseEntity<Page<EquipmentModel>> getAllEquipments(@RequestParam(defaultValue = "0", name = "page") int page,
                                                       @RequestParam(defaultValue = "5", name = "size") int size) {

        return ResponseEntity.ok(equipmentService.getAllEquipments(PageRequest.of(page, size)));
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
}