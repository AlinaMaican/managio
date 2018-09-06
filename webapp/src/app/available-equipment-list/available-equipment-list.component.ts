import {Component, OnInit} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";

@Component({
  selector: 'app-available-equipment-list',
  templateUrl: './available-equipment-list.component.html',
  styleUrls: ['./available-equipment-list.component.css']
})
export class AvailableEquipmentListComponent implements OnInit {

  equipments: EquipmentModel[];
  private availableEquipmentId: number;
  private equipmentSubscription: Subscription;
  equipment: EquipmentModel;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.equipments = equipments;
      }
    );
  }

  enableDates(event) {
    if(event.target.checked) {
      this.equipment.isChecked = true;
    }
  }
  // activateDates(event, availableEquipmentId) {
  //   if (event.target.checked) {
  //     query(isNumber(":startDate"),)
  //     // var target = event.target;
  //     // var idAttribute = target.attributes.id
  //
  //
  // }
  // }

}
