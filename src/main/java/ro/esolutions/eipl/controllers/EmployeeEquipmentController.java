package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.services.EmployeeEquipmentService;

import javax.validation.Valid;
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

    @GetMapping()
    public ResponseEntity<List<EmployeeEquipmentModel>> getAllEmployeeEquipmentsForEmployee(@RequestParam("employeeId") final Long employeeId) {
        return ResponseEntity.ok(employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(employeeId));
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
}
