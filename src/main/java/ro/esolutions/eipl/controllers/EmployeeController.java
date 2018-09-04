package ro.esolutions.eipl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.models.EquipmentModel;
import ro.esolutions.eipl.services.EmployeeService;
import ro.esolutions.eipl.services.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EquipmentService equipmentService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}/equipment")
    public ResponseEntity<List<EquipmentModel>> getAllEquipmentForEmployee(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(equipmentService.getAllEquipmentForEmployee(userId));
    }
}
