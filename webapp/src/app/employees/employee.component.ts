import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {EmployeeService} from "./employee.service";
import {Router} from "@angular/router";
import {Employee} from "./employee.model";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  private employeeSubscription: Subscription;
  employees: Employee[];
  employeeForm: FormGroup;
  file: File;

  constructor(private employeeService: EmployeeService, private router: Router,
              private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
      (employee: Employee[]) => {
        this.employees = employee;
      }
    );
  }

  createForm() {
    this.employeeForm = this.formBuilder.group({
      field_file: null
    });
  }

  upload(event) {
    this.file = event.srcElement.files[0];

    this.saveFile();
  }


  saveFile() {
    if (this.file !== undefined) {
      this.employeeService.saveFile(this.file).subscribe(() => {
        location.reload();
      });
    }
  }
}
