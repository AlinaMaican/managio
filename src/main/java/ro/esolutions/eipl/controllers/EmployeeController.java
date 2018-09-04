package ro.esolutions.eipl.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.services.EmployeeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @NonNull
    private final EmployeeService employeeService;
    private static final String BINDING_RESULT_ERROR_MESSAGE = "Employee not valid";


    @GetMapping("/all")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

//    @PostMapping
//    public ResponseEntity<Object> addNewEmployee(@RequestBody @Valid EmployeeModel employeeModel, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return ResponseEntity.badRequest().body(Collections.singletonMap("error", BINDING_RESULT_ERROR_MESSAGE));
//        }
//        employeeModel.setId(null);
//        return ResponseEntity.ok(employeeService.addNewEmployee(employeeModel));
//
//    }

    @PostMapping(value = "/file")
    public ResponseEntity<Object> uploadEmployeeFromCSV(@RequestPart("file") final MultipartFile file){
        return ResponseEntity.ok(employeeService.uploadEmployeeFromCSV(file));
    }
}