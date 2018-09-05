import {Component, OnInit} from '@angular/core';
import {EmployeeEquipmentModel} from "./employee-equipment.model";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeEquipmentService} from "./employeeEquipment.service";

@Component({
  selector: 'app-employee-equipments-list',
  templateUrl: './employee-equipments-list.component.html',
  styleUrls: ['./employee-equipments-list.component.css']
})
export class EmployeeEquipmentsListComponent implements OnInit {

  employeeEquipments: EmployeeEquipmentModel[];

  constructor(private route: ActivatedRoute, private router: Router, private employeeEquipmentService: EmployeeEquipmentService) { }

  ngOnInit(): void {}
}
