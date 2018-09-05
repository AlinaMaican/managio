import {Component, OnInit, ViewChild} from '@angular/core';
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

  availableEquipments: EquipmentModel[];
  private equipmentSubscription: Subscription;
  enableDates : boolean = true;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.availableEquipments = equipments;
      }
    );
  }

  activateDates() {
    var checkbox = document.getElementById("myCheckbox");
    this.enableDates = checkbox.valueOf() != true;
  }
}
