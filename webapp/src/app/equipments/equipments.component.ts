import { Component, OnInit} from '@angular/core';
import { Subscription} from "rxjs";
import { EquipmentModel} from "./model/equipment.model";
import { EquipmentService} from "./equipment.service";
import { Router} from "@angular/router";

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit {

  private equipmentSubscription: Subscription;
  equipments: EquipmentModel[];

  constructor(private equimentService: EquipmentService, private router: Router) { }

  ngOnInit(): void {
    this.equipmentSubscription = this.equimentService.getAllEquipments().subscribe(
      (equipment: EquipmentModel[]) => {
        this.equipments = equipment;
      }
    );
  }

  ngOnDestroy(): void {
    this.equipmentSubscription.unsubscribe();
  }
}
