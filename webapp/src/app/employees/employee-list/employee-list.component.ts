import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs/internal/Subscription";
import {Employee} from "../employee.model";
import {EmployeeService} from "../employee.service";
import {Router} from "@angular/router";


@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit, OnDestroy{
  private employeeSubscription: Subscription;
  employees:Employee[];

  constructor(private employeeService: EmployeeService, private router: Router){
  }

  ngOnInit(): void{
    this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
      (employee: Employee[]) => {
      this.employees = employee;
    }
    );
}

  ngOnDestroy(): void {
    this.employeeSubscription.unsubscribe()
  }
}
