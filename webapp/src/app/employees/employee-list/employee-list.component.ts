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
  private employeeSubscription1: Subscription;
  employeeSubscription2: Subscription;
  employees: Employee[];
  groupedEmployees: Employee[][];
  stringForm: FormGroup;

  constructor(private employeeService: EmployeeService, private router: Router) {
  }
  ngOnInit(): void {

      this.employeeSubscription1 = this.employeeService.getAllEmployees().subscribe(
        (employee: Employee[]) => {
          this.employees = employee;
          this.groupedEmployees = [];

          for (let i = 0; i < this.employees.length; i = i + 4) {
            this.groupedEmployees.push(this.employees.slice(i, i + 4))
          }
        }
      );
      this.initForm();
  }
  initForm() {
    this.stringForm = new FormGroup({
      'stringValue': new FormControl('')
    });
  }

  fill():void{
    //alert(this.stringForm.get("stringValue").value);
    this.employeeSubscription2 = this.employeeService.getEmployeesByQuery(this.stringForm.get("stringValue").value).subscribe(
      (employee: Employee[]) => {
        this.employees = employee;
        this.groupedEmployees = [];

        for (let i = 0; i < this.employees.length; i = i + 4) {
          this.groupedEmployees.push(this.employees.slice(i, i + 4))
        }
      }
    );
   // alert("BAU");
  }

  ngOnDestroy(): void {
    this.employeeSubscription1.unsubscribe()
  }
}
