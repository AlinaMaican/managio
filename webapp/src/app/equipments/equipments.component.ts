import { Component, OnInit,OnDestroy} from '@angular/core';
import { Subscription} from "rxjs";
import { EquipmentModel} from "./model/equipment.model";
import { EquipmentService} from "./equipment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit,OnDestroy{

   equipmentSubscription: Subscription = null;
   addequipment:FormGroup;
   equipments: EquipmentModel[];
   REGEX_NAME: string = '^[A-Z0-9]+';

  constructor(private route:ActivatedRoute,private router: Router,private equipmentService: EquipmentService) { }

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
      'size': new FormControl('',[ Validators.required, Validators.pattern(this.REGEX_NAME)]),
      'sex': new FormControl('', Validators.required),
    });
  }
   createEquipment(){
    let equipmentObject: EquipmentModel = this.addequipment.value;
    this.equipments.unshift(equipmentObject);
    this.equipmentSubscription =
       this.equipmentService.addEquipment(equipmentObject).subscribe(
         () => {
           this.router.navigate(['/equipment/all']);
         }
         );
   }

  ngOnDestroy(): void {
    if (this. equipmentSubscription !== null) {
      this. equipmentSubscription.unsubscribe();

   }

  }
}
