package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;
import ro.esolutions.eipl.services.EmployeeEquipmentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/employeeEquipment")
@RequiredArgsConstructor
public class EmployeeEquipmentController {

    @NonNull
    private final EmployeeEquipmentService employeeEquipmentService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeesEquipments() {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeesEquipments());
    }

    @GetMapping("/reports/expiringReports")
    public ResponseEntity<List<EmployeeEquipmentReportModel>> getAllEmployeesEquipmentsReport() {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeesEquipmentsReport());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeeEquipmentsForEmployee(@RequestParam("employeeId") Long employeeId) {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(employeeId));
    }

    @GetMapping("/reports/expiringReports/download")
    public ResponseEntity<Object> downloadFile() {
        String contentType = "application/octet-stream";

        String CSVString = employeeEquipmentService.exportCSV();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "export.csv" + "\"")
                .body(CSVString);
    }

}
