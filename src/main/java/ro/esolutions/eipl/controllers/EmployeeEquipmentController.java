package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeesEquipments() {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeesEquipments());
    }

    @GetMapping("/reports/expiring")
    public ResponseEntity<List<EmployeeEquipmentReportModel>> getAllEmployeeEquipmentsReport() {
        return ResponseEntity.ok(employeeEquipmentService.getExpiringEmployeeEquipmentsReport());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeeEquipmentsForEmployee(@RequestParam("employeeId") final Long employeeId) {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(employeeId));
    }

    @GetMapping(value = "/reports/expiring/download")
    public ResponseEntity<Object> downloadExpiringEquipmentEmployeesCsvReport() {
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
    public void deleteEmployeeEquipment(@PathVariable("id") final long id){
        employeeEquipmentService.deleteEmployeeEquipmentById(id);
    }

    @PostMapping("/saveAllocatedEquipments/{employeeId}")
    public ResponseEntity<Object> saveAllocatedEquipments(@RequestBody List<EmployeeEquipmentModel> allocatedEquipments,
                                                          @PathVariable("employeeId") final Long employeeId) {
        employeeEquipmentService.saveAllocatedEquipments(allocatedEquipments, employeeId);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }
}
