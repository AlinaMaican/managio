import { Component, OnInit, OnDestroy} from '@angular/core';
import { Subscription} from "rxjs";
import { Employee} from "./employee.model";
import { EmployeeService} from "./employee.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit,OnDestroy {

  private employeeSubscription: Subscription;
  employees: Employee[];
  employeeForm: FormGroup;
  file: File;
  groupedEmployees: Employee[][];

  constructor(private route: ActivatedRoute, private employeeService: EmployeeService, private router: Router,
              private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
      (employee: Employee[]) => {
        this.employees = employee;
        this.groupedEmployees = [];

        for (let i = 0; i < this.employees.length; i = i + 4) {
          this.groupedEmployees.push(this.employees.slice(i, i + 4))
        }
      }
    );
  }

  ngOnDestroy(): void {
    if (this.employeeSubscription !== null) {
      this.employeeSubscription.unsubscribe();

    }
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
