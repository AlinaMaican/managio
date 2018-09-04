import { Component, OnInit, OnDestroy} from '@angular/core';
import { Subscription} from "rxjs";
import { EquipmentModel} from "./model/equipment.model";
import { EquipmentService} from "./equipment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit,OnDestroy {

  private equipmentSubscription: Subscription;
  addequipment: FormGroup;
  equipments: EquipmentModel[];
  REGEX_NAME: string = '^[A-Z0-9]+';
  optionArray: string[] = ["MABEC_01", "MABEC_02", "MABEC_03", "MABEC_04", "MABEC_05", "MABEC_06", "MABEC_07"];
  equipmentForm: FormGroup;
  file: File;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService, private router: Router,
              private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.equipmentSubscription = this.equipmentService.getAllEquipments().subscribe(
      (equipment: EquipmentModel[]) => {
        this.equipments = equipment;
      }
    );
    this.initForm();
  }

  initForm() {
    this.addequipment = new FormGroup({
      'name': new FormControl('', Validators.required),
      'code': new FormControl('', Validators.required),
      'mabecCode': new FormControl('', Validators.required),
      'protectionType': new FormControl('', Validators.required),
      'size': new FormControl('', [Validators.required, Validators.pattern(this.REGEX_NAME)]),
      'sex': new FormControl('', Validators.required),
    });
    console.log(this.optionArray[0]);
  }

  createEquipment() {
    let equipmentObject: EquipmentModel = this.addequipment.value;
    this.equipments.unshift(equipmentObject);
    this.equipmentSubscription =
      this.equipmentService.addEquipment(equipmentObject).subscribe(
        () => {
          this.router.navigate(['/api/equipment/all']);
        }
      );
  }

  ngOnDestroy(): void {
    if (this.equipmentSubscription !== null) {
      this.equipmentSubscription.unsubscribe();

    }
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
