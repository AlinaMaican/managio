import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {ExpiringEquipmentModel} from "./expiring-equipment.model";
import {ExpiringEquipmentService} from "./expiring-equipment.service";
import {EmployeeEquipmentModel} from "../../employee-equipments-list/employee-equipment.model";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeService} from "../../employees/employee.service";
import {FormBuilder} from "@angular/forms";
import {EmployeeEquipmentService} from "../../employee-equipments-list/employeeEquipment.service";


@Component({
  selector: 'app-expiring-equipment',
  templateUrl: './expiring-equipment.component.html',
  styleUrls: ['./expiring-equipment.component.css']
})
export class ExpiringEquipmentsComponent implements OnInit, OnDestroy{

  private employeeEquipmentSubscription: Subscription;
  employeesEquipments: ExpiringEquipmentModel[];

  constructor(private route: ActivatedRoute,
              private expiringEquipmentService: ExpiringEquipmentService,
              private router: Router,
              private formBuilder: FormBuilder) {
  }
  ngOnInit(): void {
    this.employeeEquipmentSubscription = this.expiringEquipmentService.getAllExpiringEquipment().subscribe(
      (employeeEquipment: ExpiringEquipmentModel[]) => {
        this.employeesEquipments = employeeEquipment;
      }
    )
  }

  ngOnDestroy(): void {
    if (this.employeeEquipmentSubscription !== null) {
      this.employeeEquipmentSubscription.unsubscribe();
    }
  }
}
