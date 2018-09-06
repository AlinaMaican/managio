package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;
import ro.esolutions.eipl.services.EmployeeEquipmentService;

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
}
