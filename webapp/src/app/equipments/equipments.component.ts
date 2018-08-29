import { Component, OnInit} from '@angular/core';
import { Subscription} from "rxjs";
import { Equipment} from "../users/model/equipment";
import { EquipmentService} from "./equipment.service";
import { Router} from "@angular/router";

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit {

  private equipmentSubscription: Subscription;
  equipment: Equipment[];

  constructor(private equimentService: EquipmentService, private router: Router) { }

  ngOnInit(): void {
    this.equipmentSubscription = this.equimentService.getAllEquipments().subscribe(
      (equipment: Equipment[]) => {
        this.equipment = equipment;
      }
    );
  }

  ngOnDestroy(): void {
    this.equipmentSubscription.unsubscribe();
  }
}
