package ro.esolutions.eipl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.services.EmployeeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
   @GetMapping
    public ResponseEntity<Set<EmployeeModel>> getFilteredEmployees(@RequestParam("Parameter") String searchValue) {
       return ResponseEntity.ok(employeeService.getFilteredEmployees(searchValue));
   }

}
