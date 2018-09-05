import {Component, OnDestroy, OnInit} from '@angular/core';
import {EquipmentService} from "../../equipments/equipment.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs/internal/Subscription";
import {EquipmentModel} from "../../equipments/model/equipment.model";
import {EmployeeEquipmentService} from "../../employee-equipment/employee-equipment.service";
import {EmployeeEquipmentModel} from "../../employee-equipment/employee-equipment.model";

@Component({
  selector: 'app-equipment',
  templateUrl: './employee-equipment.component.html',
  styleUrls: ['./employee-equipment.component.css']
})
export class EmployeeEquipmentComponent implements OnInit, OnDestroy {
  private employeeId: number;
  private employeeEquipmentSubscription: Subscription;
  private employeeEquipmentList: EmployeeEquipmentModel[];

  constructor(private employeeEquipmentService: EmployeeEquipmentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.employeeId = +params['id'];
    });
    this.employeeEquipmentSubscription = this.employeeEquipmentService.getEquipmentByEmployeeId(this.employeeId).subscribe(
      (employeeEquipments) => {
        this.employeeEquipmentList = employeeEquipments;
      }
    );
  }

  ngOnDestroy(): void {
    this.employeeEquipmentSubscription.unsubscribe();
  }

}
