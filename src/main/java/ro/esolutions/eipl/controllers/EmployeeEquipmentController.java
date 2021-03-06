package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;
import ro.esolutions.eipl.services.EmployeeEquipmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employeeEquipment")
@RequiredArgsConstructor
public class EmployeeEquipmentController {

    private static final String OCTET_CONTENT_TYPE = "application/octet-stream";

    public static final String JSON_EMPTY_BODY = "{}";
    @NonNull
    private final EmployeeEquipmentService employeeEquipmentService;

    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeEquipmentReportModel>> getAssignedEmployeeEquipments(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size) {
        return ResponseEntity.ok(employeeEquipmentService.getAssignedEmployeeEquipmentsReportPaginated(page, size));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeEquipmentReportModel>> getAllAssignedEmployeeEquipments() {
        return ResponseEntity.ok(employeeEquipmentService.getAssignedEmployeeEquipmentsReport());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeeEquipmentsForEmployee(@RequestParam("employeeId") final Long employeeId) {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(employeeId));
    }

    @GetMapping("/employeeEquipmentsAboutToEndThisAWeek")
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeeEquipmentAboutToEndThisWeek(){
        return ResponseEntity.ok(employeeEquipmentService.getEmployeeEquipmentAboutToExpireThisWeek());
    }

    @GetMapping(value = "/reports/assigned/download")
    public ResponseEntity<Object> downloadAssignedEquipmentEmployeesCsvReport() {
        String CSVString = employeeEquipmentService.exportCSV();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(OCTET_CONTENT_TYPE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "export.csv" + "\"")
                .body(CSVString);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEquipmentModel> updateEmployeeEquipment(
            @PathVariable("id") final long id,
            @RequestBody @Valid final EmployeeEquipmentModel employeeEquipment) {
        employeeEquipment.setId(id);
        return ResponseEntity.ok(employeeEquipmentService.updateEmployeeEquipment(employeeEquipment));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteEmployeeEquipment(@PathVariable("id") final long id) {
        employeeEquipmentService.deleteEmployeeEquipmentById(id);
    }

    @PostMapping("/saveAllocatedEquipments/{employeeId}")
    public ResponseEntity saveAllocatedEquipments(@RequestBody final List<EmployeeEquipmentModel> allocatedEquipments,
                                                          @PathVariable("employeeId") final Long employeeId) {
        employeeEquipmentService.saveAllocatedEquipments(allocatedEquipments, employeeId);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }
}