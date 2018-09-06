import {Component, OnInit} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-available-equipment-list',
  templateUrl: './available-equipment-list.component.html',
  styleUrls: ['./available-equipment-list.component.css']
})
export class AvailableEquipmentListComponent implements OnInit {

  equipments: EquipmentModel[];
  private equipmentSubscription: Subscription;
  selectedEquipments: EquipmentModel[] = [];
  stringForm: FormGroup;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.equipments = equipments;
      }
    );
    this.initForm();
  }

  initForm() {
    this.stringForm = new FormGroup({
      'searchValue': new FormControl('')
    });
  }

  setEquipments(equipment: EquipmentModel){
      this.selectedEquipments.push(equipment);
  }

  allocateEquipmentsToEmployee() {
    debugger;
    this.equipmentService.saveAllocatedEquipments(this.selectedEquipments).subscribe(() => {
        this.router.navigate(['/#/equipments']);
      }
    );
  }

  fill(): void {
    this.equipmentSubscription.unsubscribe();
    this.equipmentSubscription = this.equipmentService.getFilteredEquipments(this.stringForm.get("searchValue").value).subscribe(
      (equipmentModels: EquipmentModel[]) => {
        this.equipments = equipmentModels;
      });
  }
}
