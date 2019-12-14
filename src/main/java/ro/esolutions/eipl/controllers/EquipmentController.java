package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.mappers.EquipmentMapper;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.repositories.EquipmentRepository;
import ro.esolutions.eipl.services.EquipmentService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private static final String BINDING_RESULT_ERROR_MESSAGE = "Equipment not valid";
    public static final String JSON_EMPTY_BODY = "{}";

    @NonNull
    private final EquipmentService equipmentService;

    @NonNull
    private final EquipmentRepository equipmentRepository;

    @GetMapping("/all")
    public ResponseEntity<Page<EquipmentModel>> getAllEquipments(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                 @RequestParam(defaultValue = "5", name = "size") int size) {
        return ResponseEntity.ok(equipmentService.getAllEquipments(PageRequest.of(page, size)));
    }
    @GetMapping("/allExpired")
    public ResponseEntity<Page<EquipmentModel>> getAllExpiredEquipments(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                        @RequestParam(defaultValue = "5", name = "size") int size){
            return ResponseEntity.ok(equipmentService.getAllExpiredEquipments(PageRequest.of(page, size)));

    }
    @GetMapping("/lastAddedEquipments")
    public ResponseEntity<List<EquipmentModel>> getLastAddedEquipments(){
        return ResponseEntity.ok(equipmentService.getLastAddedEquipments());
    }

    @GetMapping("/equipmentsAboutToExpireThisWeek")
    public ResponseEntity<List<EquipmentModel>> getEquipmentsAboutToExpireThisAWeek(){
        return ResponseEntity.ok(equipmentService.getEquipmentsAboutToExpireThisWeek());
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
        try {
            equipmentService.uploadEquipmentFromCSV(file);
            return ResponseEntity.ok(JSON_EMPTY_BODY);
        }
        catch(Exception e){
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.TEXT_PLAIN);
            header.set("text", equipmentService.getMessage(e));
            return ResponseEntity.badRequest().headers(header).build();
        }

    }

    @GetMapping("/reports/unused")
    public ResponseEntity<Object> getAllUnusedEquipments(@RequestParam(name = "type", required = false) final MediaType mediaType) {
        if (new MediaType("text", "csv").isCompatibleWith(mediaType)) {
            return ResponseEntity.ok(
                    equipmentService.getAllUnusedEquipments().stream()
                            .map(EquipmentMapper::fromModelToCsvString)
                            .collect(Collectors.joining("\r\n")));
        }
        return ResponseEntity.ok(equipmentService.getAllUnusedEquipments());
    }

    @GetMapping("/reports/expired")
    public ResponseEntity<Object> getAllExpiredEquipments(@RequestParam(name = "type", required = false) final MediaType mediaType) {
        if (new MediaType("text", "csv").isCompatibleWith(mediaType)) {
            return ResponseEntity.ok(
                    equipmentRepository.getAllExpiredEquipmentsAsList().stream()
                            .map(EquipmentMapper::fromEntityToCsvString)
                            .collect(Collectors.joining("\r\n")));
        }
        return ResponseEntity.ok(equipmentRepository.getAllExpiredEquipmentsAsList()
                .stream().map(EquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList()));
    }

    @GetMapping("/available")
    public ResponseEntity<List<EquipmentModel>> getAllAvailableEquipments() {
        return ResponseEntity.ok(equipmentService.getAllAvailableEquipments());
    }


    @GetMapping
    public ResponseEntity<List<EquipmentModel>> getFilteredEquipments(@RequestParam("name_contains") String searchValue) {
        return ResponseEntity.ok(equipmentService.getFilteredEquipments(searchValue));
    }

    @GetMapping("/filtered/available")
    public ResponseEntity<List<EquipmentModel>> getFilteredAvailableEquipments(@RequestParam("name_contains") String searchValue) {
        return ResponseEntity.ok(equipmentService.getFilteredAvailableEquipments(searchValue));
    }
}