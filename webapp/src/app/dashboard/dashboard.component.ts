import {Component, Input, OnInit} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {ActivatedRoute, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";
import {EmployeeEquipmentModel} from "../employee-equipment/employee-equipment.model";
import {EmployeeEquipmentService} from "../employee-equipment/employee-equipment.service";



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  lastAddedEquipments: EquipmentModel[];
  equipmentsAboutToExpire: EquipmentModel[];
  employeeEquipmentsAboutToEndInAWeek:EmployeeEquipmentModel[];


  constructor(private route: ActivatedRoute,
              private router: Router,
              private equipmentService: EquipmentService,
              private employeeEquipmentService:EmployeeEquipmentService) { }

  ngOnInit() {
    this.equipmentService.getLastAddedEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.lastAddedEquipments = equipments;
      });
    this.equipmentService.getEquipmentsABoutToExpireInAWeek().subscribe(
      (equipments: EquipmentModel[]) => {
        this.equipmentsAboutToExpire = equipments;
      });
    this.employeeEquipmentService.getEmployeeEquipmentAboutToEndInAWeek().subscribe(
      (equipments: EmployeeEquipmentModel[]) => {
        this.employeeEquipmentsAboutToEndInAWeek = equipments;
      });

  }

}
