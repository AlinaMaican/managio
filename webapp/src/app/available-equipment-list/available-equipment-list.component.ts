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
  private equipmentSubscription: Subscription;
  selectedEquipments: EquipmentModel[] = [];

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.equipments = equipments;
      }
    );
  }

  setEquipments(equipment: EquipmentModel){
    let equipmentIsNotInArray = this.selectedEquipments.indexOf(equipment) < 0;
    if(equipmentIsNotInArray) {
      this.selectedEquipments.push(equipment);
    }
  }

  allocateEquipmentsToEmployee() {
    debugger;
    this.equipmentService.saveAllocatedEquipments(this.selectedEquipments).subscribe(() => {
        this.router.navigate(['/#/equipments']);
      }
    );
  }
}
