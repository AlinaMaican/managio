import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs/internal/Subscription";
import {EmployeeEquipmentService} from "../../employee-equipment/employee-equipment.service";
import {EmployeeEquipmentModel} from "../../employee-equipment/employee-equipment.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-equipment',
  templateUrl: './employee-equipment.component.html',
  styleUrls: ['./employee-equipment.component.css']
})
export class EmployeeEquipmentComponent implements OnInit, OnDestroy {
  public employeeId: number;
  private employeeEquipmentSubscription: Subscription;
  employeeEquipmentList: EmployeeEquipmentModel[] = [];

  editEquipmentForm: FormGroup;
  targetEmployeeEquipment: EmployeeEquipmentModel;

  constructor(private employeeEquipmentService: EmployeeEquipmentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.employeeId = +params['id'];
    });
    this.loadEquipment();
    this.editEquipmentForm = new FormGroup(
      {
        'startDate': new FormControl('', Validators.required),
        'endDate': new FormControl('', Validators.required),
      }
    );
  }

  ngOnDestroy(): void {
    this.employeeEquipmentSubscription.unsubscribe();
  }

  prepareEdit(employeeEquipment: EmployeeEquipmentModel) {
    this.editEquipmentForm.patchValue({
      'startDate': employeeEquipment.startDate,
      'endDate': employeeEquipment.endDate
    });
    this.targetEmployeeEquipment = employeeEquipment;
  }

  prepareDelete(employeeEquipment: EmployeeEquipmentModel) {
    this.targetEmployeeEquipment = employeeEquipment;
  }

  private loadEquipment() {
    if (this.employeeEquipmentSubscription) {
      this.employeeEquipmentSubscription.unsubscribe();
    }
    this.employeeEquipmentSubscription = this.employeeEquipmentService.getEquipmentByEmployeeId(this.employeeId).subscribe(
      (employeeEquipments) => {
        this.employeeEquipmentList = employeeEquipments;
      }
    );
  }

  updateEmployeeEquipment() {
    if (!this.editEquipmentForm || this.editEquipmentForm.invalid) return;
    let editedObj = Object.assign({}, this.targetEmployeeEquipment);
    editedObj.startDate = this.editEquipmentForm.value.startDate;
    editedObj.endDate = this.editEquipmentForm.value.endDate;
    this.employeeEquipmentService.updateEmployeeEquipment(editedObj).subscribe(
      null, null, () => this.loadEquipment());
  }

  deleteEmployeeEquipment() {
    let deleteObject = Object.assign({}, this.targetEmployeeEquipment);
    this.employeeEquipmentService.deleteEmployeeEquipment(deleteObject).subscribe(
      null, null, () => this.loadEquipment());
  }
}
