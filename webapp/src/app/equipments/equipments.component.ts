import { Component, OnInit, OnDestroy} from '@angular/core';
import { Subscription} from "rxjs";
import { EquipmentModel} from "./model/equipment.model";
import { EquipmentService} from "./equipment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../users/model/user.model";
import {UserService} from "../users/user.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit,OnDestroy {

  public equipmentSubscription: Subscription;
  addequipment: FormGroup;
  equipments: EquipmentModel[];
  REGEX_NAME: string = '^[A-Z0-9]+';
  mabecOptionArray: string[] = ["MABEC_01", "MABEC_02", "MABEC_03", "MABEC_04", "MABEC_05", "MABEC_06", "MABEC_07"];
  protectionOptionArray: string[] = ["HELMET","CLOTHING","FOOTWEAR"];
  equipmentForm: FormGroup;
  file: File;
  loggedUser:User;
  isAdminOrManager=false;
  public baseURL: string = 'equipment';

  constructor(private route: ActivatedRoute, public equipmentService: EquipmentService, private router: Router,
              private formBuilder: FormBuilder,private service:UserService) {
    this.createForm();
  }

  ngOnInit(): void {
    let today = new Date().toISOString().split('T')[0];
    document.getElementById("expirationDate").setAttribute('min', today);
    this.service.getAuthUser().subscribe(
      (user: User) => {
        this.loggedUser=user;
        if(this.loggedUser.userRole.toLocaleString()  === "ADMIN" || this.loggedUser.userRole.toLocaleString()  === "MANAGER" ){
          this.isAdminOrManager=true;
        }
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
      'expirationDate': new FormControl('',Validators.required),
      'sex': new FormControl('', Validators.required),
    });
    //console.log(this.mabecOptionArray[0]);
  }

  createEquipment() {
    let equipmentObject: EquipmentModel = this.addequipment.value;

    this.equipments = this.equipments || [];
    this.equipments.unshift(equipmentObject);
    this.equipmentSubscription =
      this.equipmentService.addEquipment(equipmentObject).subscribe(
        () => {
          this.router.navigate(['/api/equipment/all']);
        }
      );
  }

  ngOnDestroy(): void {

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
      this.equipmentService.saveFile(this.file).subscribe(
        res => {
            location.reload()
      },
          (error:HttpErrorResponse)=>{
          if(error.status===400){
            alert(error.headers.get("text"));
          }
          });
    }
  }

  getPaginatedList(paginatedList: EquipmentModel[]) {
    this.equipments = paginatedList;
  }
}
