import {Component, OnDestroy, OnInit} from '@angular/core';
import {EquipmentService} from "../../equipments/equipment.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs/internal/Subscription";
import {EquipmentModel} from "../../equipments/model/equipment.model";

@Component({
  selector: 'app-equipment',
  templateUrl: './employee-equipment.component.html',
  styleUrls: ['./employee-equipment.component.css']
})
export class EmployeeEquipmentComponent implements OnInit, OnDestroy {
  private employeeId: number;
  private equipmentSubscription: Subscription;
  private equipments: EquipmentModel[];

  constructor(private equipmentService: EquipmentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.employeeId = +params['id'];
    });
    this.equipmentSubscription = this.equipmentService.getAllEquipmentByEmployeeId(this.employeeId).subscribe(
      (equipments) => {
        this.equipments = equipments;
      }
    );
  }

  ngOnDestroy(): void {
    this.equipmentSubscription.unsubscribe();
  }

}
