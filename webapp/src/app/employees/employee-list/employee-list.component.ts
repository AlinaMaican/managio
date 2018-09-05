import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs/internal/Subscription";
import {Employee} from "../employee.model";
import {EmployeeService} from "../employee.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit, OnDestroy {
  employeeSubscription: Subscription;
  employees: Employee[];
  groupedEmployees: Employee[][];
  stringForm: FormGroup;

  constructor(private employeeService: EmployeeService, private router: Router) {
  }
  ngOnInit(): void {

      this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
        (employee: Employee[]) => this.employeesGrid(employee));
      this.initForm();
  }
  initForm() {
    this.stringForm = new FormGroup({
      'searchValue': new FormControl('')
    });
  }

  fill():void{
    this.employeeSubscription.unsubscribe();
    this.employeeSubscription = this.employeeService.getFilteredEmployees(this.stringForm.get("searchValue").value).subscribe(
      (employee: Employee[]) => this.employeesGrid(employee));
  }

  employeesGrid(employee: Employee[]): void{

    this.employees = employee;
    this.groupedEmployees = [];

    for (let i = 0; i < this.employees.length; i = i + 4) {
      this.groupedEmployees.push(this.employees.slice(i, i + 4))
    }

  }

  ngOnDestroy(): void {
    this.employeeSubscription.unsubscribe()
  }
}
