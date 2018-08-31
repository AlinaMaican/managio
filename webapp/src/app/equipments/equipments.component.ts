import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Subscription} from "rxjs";
import { EquipmentModel} from "./model/equipment.model";
import { EquipmentService} from "./equipment.service";
import { Router} from "@angular/router";
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
  loading: boolean = false;

  @ViewChild('fileInput') fileInput: ElementRef;

  constructor(private equimentService: EquipmentService, private router: Router,
              private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.equipmentSubscription = this.equimentService.getAllEquipments().subscribe(
      (equipment: EquipmentModel[]) => {
        this.equipments = equipment;
      }
    );
  }

  createForm() {
    this.equipmentForm = this.formBuilder.group({

    });
  }

  onFileChange(event) {
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.equipmentForm.get('').setValue({
          filename: file.name,
          fileType: file.type,
          value: reader.result.split(',')[1]
        })
      };
    }
  }

  onSubmit() {
    this.loading = true;
    setTimeout(() => {
      alert('done!');
      this.loading = false;
    }, 1000);
  }

  ngOnDestroy(): void {
    this.equipmentSubscription.unsubscribe();
  }
}
