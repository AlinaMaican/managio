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
  groupedEmployees:Employee[][];

  constructor(private employeeService: EmployeeService, private router: Router){
  }

  ngOnInit(): void{
    this.employeeSubscription = this.employeeService.getAllEmployees().subscribe(
      (employee: Employee[]) => {
      this.employees = employee;


      this.groupedEmployees = [];
      let numberOfGroups = Math.floor(this.employees.length/4);
      for(let i = 0; i < numberOfGroups; i++) {
        this.groupedEmployees.push([this.employees[i*4], this.employees[i*4+1],this.employees[i*4+2],this.employees[i*4+3]])
      }


      let remainingEmployees = [];
      let firstRemaingEmployeeIndex = this.employees.length - this.employees.length%4;
      for(let i = firstRemaingEmployeeIndex; i < this.employees.length; i++) {
        remainingEmployees.push(this.employees[i]);
      }
      this.groupedEmployees.push(remainingEmployees);
    }
    );
}

  ngOnDestroy(): void {
    this.employeeSubscription.unsubscribe()
  }
}
