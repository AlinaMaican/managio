import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {ExpiringEquipmentModel} from "./expiring-equipment.model";
import {ExpiringEquipmentService} from "./expiring-equipment.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-expiring-equipment',
  templateUrl: './expiring-equipment.component.html',
  styleUrls: ['./expiring-equipment.component.css']
})
export class ExpiringEquipmentsComponent implements OnInit, OnDestroy{

  private employeeEquipmentSubscription: Subscription;
  expiringEquipments: ExpiringEquipmentModel[];

  constructor(private route: ActivatedRoute, private expiringEquipmentService: ExpiringEquipmentService) {
  }
  ngOnInit(): void {
    this.employeeEquipmentSubscription = this.expiringEquipmentService.getAllExpiringEquipment().subscribe(
      (employeeEquipment: ExpiringEquipmentModel[]) => {
        this.expiringEquipments = employeeEquipment;
      }
    )
  }

  ngOnDestroy(): void {
    if (this.employeeEquipmentSubscription !== null) {
      this.employeeEquipmentSubscription.unsubscribe();
    }
  }
}
