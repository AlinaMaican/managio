import { Component, OnInit, OnDestroy} from '@angular/core';
import { Subscription} from "rxjs";
import { Employee} from "./employee.model";
import { EmployeeService} from "./employee.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {User} from "../users/model/user.model";
import {UserService} from "../users/user.service";
import {HttpErrorResponse} from "@angular/common/http";

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
  stringForm: FormGroup;
  loggedUser:User;
  isAdminOrManager=false;

  constructor(private route: ActivatedRoute, private employeeService: EmployeeService, private router: Router,
              private formBuilder: FormBuilder,private service:UserService) {
    this.createForm();
  }

  ngOnInit(): void {
    this.service.getAuthUser().subscribe(
      (user: User) => {
        this.loggedUser=user;
        if(this.loggedUser.userRole.toLocaleString()  === "ADMIN" || this.loggedUser.userRole.toLocaleString()  === "MANAGER" ){
          this.isAdminOrManager=true;
        }
      }
    );
    this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
      (employee: Employee[]) => this.employeesGrid(employee));
    this.initForm();
  }

  initForm() {
    this.stringForm = new FormGroup({
      'searchValue': new FormControl('')
    });
  }

  fill(): void {
    //this.employeeSubscription.unsubscribe();
     this.employeeService.getFilteredEmployees(this.stringForm.get("searchValue").value)
       .subscribe(
      res => {
          this.employeesGrid(res)
      },
         (error:HttpErrorResponse) => {
           if(error.status === 400){
             alert("You found me!");
           }
         });
  }

  employeesGrid(employee: Employee[]): void {

    this.employees = employee;
    this.groupedEmployees = [];

    for (let i = 0; i < this.employees.length; i = i + 4) {
      this.groupedEmployees.push(this.employees.slice(i, i + 4))
    }

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
