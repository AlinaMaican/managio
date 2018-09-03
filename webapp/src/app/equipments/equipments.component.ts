import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";
import {EquipmentService} from "./equipment.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit {

  private equipmentSubscription: Subscription;
  equipments: EquipmentModel[];
  equipmentForm: FormGroup;
  file: File;

  constructor(private equipmentService: EquipmentService, private router: Router,
              private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.equipmentSubscription = this.equipmentService.getAllEquipments().subscribe(
      (equipment: EquipmentModel[]) => {
        this.equipments = equipment;
      }
    );
  }

  createForm() {
    this.equipmentForm = this.formBuilder.group({
      field_file: null
    });
  }

  upload(event) {
    this.file = event.srcElement.files[0];

    this.saveFile();
  }


  saveFile() {
    if (this.file !== undefined) {
      this.equipmentService.saveFile(this.file).subscribe(() => {
        location.reload();
      });
    }
  }
}
