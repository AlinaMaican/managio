package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.services.EmployeeService;
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
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employee_id") final Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping(value = "/importByFile")
    public ResponseEntity<Object> uploadEmployeeFromCSV(@RequestPart("file") final MultipartFile file) {
        employeeService.uploadEmployeeFromCSV(file);
        return ResponseEntity.ok(JSON_EMPTY_BODY);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<EmployeeModel> editEmployeeById(@RequestParam(name = "helmetSize",defaultValue = "")final String helmetSize,
                                                          @RequestParam(name = "clothingSize",defaultValue = "")final String clothingSize,
                                                          @RequestParam(name = "footwearSize",defaultValue = "")final String footwearSize,
                                                          @PathVariable("employee_id") final Long employeeId) {

        EmployeeModel employeeModel = employeeService.getEmployeeById(employeeId);
        employeeModel.setHelmetSize(helmetSize);
        employeeModel.setClothingSize(clothingSize);
        employeeModel.setFootwearSize(footwearSize);
        return ResponseEntity.ok(employeeService.editEmployeeById(employeeModel));
    }
}