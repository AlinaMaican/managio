import {Component, OnInit} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-available-equipment-list',
  templateUrl: './available-equipment-list.component.html',
  styleUrls: ['./available-equipment-list.component.css']
})
export class AvailableEquipmentListComponent implements OnInit {

  private employeeId: number;
  equipments: EquipmentModel[];
  private equipmentSubscription: Subscription;
  selectedEquipments: EquipmentModel[] = [];
  stringForm: FormGroup;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.employeeId = +params['id'];
    });
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

  setEquipments(equipment: EquipmentModel) {
    equipment.isChecked = !equipment.isChecked;
    let equipmentIndex = this.selectedEquipments.indexOf(equipment)
    let equipmentIsNotInArray = equipmentIndex < 0;
    if (equipmentIsNotInArray) {
      this.selectedEquipments.push(equipment);
    }
    if (!equipment.isChecked) {
      this.selectedEquipments.splice(equipmentIndex, 1);
      equipment.startDate = null;
      equipment.endDate = null;
    }
  }

  allocateEquipmentsToEmployee() {
    this.equipmentService.saveAllocatedEquipments(this.selectedEquipments, this.employeeId).subscribe(
      () => {
        this.router.navigateByUrl('/equipments');
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
