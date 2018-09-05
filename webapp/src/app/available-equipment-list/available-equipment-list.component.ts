import {Component, OnInit, ViewChild} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-available-equipment-list',
  templateUrl: './available-equipment-list.component.html',
  styleUrls: ['./available-equipment-list.component.css']
})
export class AvailableEquipmentListComponent implements OnInit {

  availableEquipments: EquipmentModel[];
  private equipmentSubscription: Subscription;
  datesEditable = true;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.availableEquipments = equipments;
      }
    );
  }

  activateDates(event) {
    if (event.target.checked) {
      this.datesEditable = false;
    } else {
      this.datesEditable = true;
    }
  }


  updateEquipment() {

  }
}
