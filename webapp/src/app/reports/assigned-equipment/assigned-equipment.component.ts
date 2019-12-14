import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {AssignedEquipmentService} from "./assigned-equipment.service";
import {AssignedEquipmentModel} from "./assigned-equipment.model";
import {User} from "../../users/model/user.model";
import {UserService} from "../../users/user.service";
import {EquipmentModel} from "../../equipments/model/equipment.model";

@Component({
  selector: 'app-assigned-equipment',
  templateUrl: './assigned-equipment.component.html',
  styleUrls: ['./assigned-equipment.component.css']
})
export class AssignedEquipmentComponent implements OnInit {
  public employeeEquipmentSubscription: Subscription;
  assignedEquipments: AssignedEquipmentModel[];
  isConsultant=false;
  baseURL: string = 'reports/assigned-equipments';

  constructor(private route: ActivatedRoute,
              public assignedEquipmentService: AssignedEquipmentService,
              private service: UserService) {
  }

  ngOnInit() {
    this.service.getAuthUser().subscribe(
      (user: User) => {
        if(user.userRole.toLocaleString()  === "CONSULTANT"){
          this.isConsultant=true;
        }
      }
    );
    if(this.isConsultant === false){
      this.assignedEquipmentService.getAllAssignedEquipment().subscribe(
        (equipments:AssignedEquipmentModel[]) => {
          this.assignedEquipments = equipments;
        });
    }
  }

  getPaginatedList(paginatedList: AssignedEquipmentModel[]) {
    this.assignedEquipments = paginatedList;
  }
}

