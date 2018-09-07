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
  public employeeEquipmentList: EmployeeEquipmentModel[];

  public editEquipmentForm: FormGroup;
  public editTargetObject: EmployeeEquipmentModel;

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

  updateModal(employeeEquipment: EmployeeEquipmentModel) {
    this.editEquipmentForm.patchValue({
      'startDate': employeeEquipment.startDate,
      'endDate': employeeEquipment.endDate
    });
    this.editTargetObject = employeeEquipment;
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
    let editedObj = Object.assign({}, this.editTargetObject);
    editedObj.startDate = this.editEquipmentForm.value.startDate;
    editedObj.endDate = this.editEquipmentForm.value.endDate;
    this.employeeEquipmentService.updateEmployeeEquipment(editedObj).subscribe(
      () => {
        this.loadEquipment();
      }
    );
  }
}
