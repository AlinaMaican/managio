package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.services.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    public static final String JSON_EMPTY_BODY = "{}";

    @NonNull
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
   @GetMapping
    public ResponseEntity<List<EmployeeModel>> getFilteredEmployees(@RequestParam("name_contains") String searchValue) {
       return ResponseEntity.ok(employeeService.getFilteredEmployees(searchValue));
   }

    @GetMapping("/{employee_id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employee_id") Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping(value = "/importByFile")
    public ResponseEntity<Object> uploadEmployeeFromCSV(@RequestPart("file") final MultipartFile file) {
        employeeService.uploadEmployeeFromCSV(file);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<Object> editEmployeeById(@RequestBody @Valid final EmployeeModel employeeModel,
                                               final BindingResult bindingResult,
                                               @PathVariable("employee_id") final Long employeeId) {
        employeeModel.setId(employeeId);
        return ResponseEntity.ok(employeeService.editEmployeeById(employeeId, employeeModel));
    }
}