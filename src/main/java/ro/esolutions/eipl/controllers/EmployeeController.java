package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.services.EmployeeService;

import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Set<EmployeeModel>> getFilteredEmployees(@RequestParam("Parameter") String searchValue) {
       return ResponseEntity.ok(employeeService.getFilteredEmployees(searchValue));
   }

    @PostMapping(value = "/importByFile")
    public ResponseEntity<Object> uploadEmployeeFromCSV(@RequestPart("file") final MultipartFile file) {
        employeeService.uploadEmployeeFromCSV(file);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }
}