import { Component, OnInit} from '@angular/core';
import { Subscription} from "rxjs";
import { Equipment} from "../users/model/equipment";
import { EquipmentService} from "./equipment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit {

  private equipmentSubscription: Subscription;
  public addEquipment:FormGroup;
  equipments: Equipment[];

  constructor(private equipmentService: EquipmentService, private router: Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.equipmentSubscription = this.equipmentService.getAllEquipments().subscribe(
      (equipment: Equipment[]) => {
        this.equipments = equipment;
        this.initForm();
      }
    );
  }
   createEquipment(){
     let equipmentObject: Equipment = this.addEquipment.value;

     this.equipmentSubscription =
       this.equipmentService.addEquipment(equipmentObject).subscribe(
         () => {
           this.router.navigate(['/']);
         }
       );
   }

  initForm() {
    this.addEquipment = new FormGroup({
      'name': new FormControl('', Validators.required),
      'code': new FormControl('', Validators.required),
      'mabecCode': new FormControl('', Validators.required),
      'protectionType': new FormControl('', Validators.required),
      'size': new FormControl('', Validators.required),
      'sex': new FormControl('', Validators.required),
    });
  }

  ngOnDestroy(): void {
    if (this. equipmentSubscription !== null) {
      this. equipmentSubscription.unsubscribe();
    }
   
  }
}
