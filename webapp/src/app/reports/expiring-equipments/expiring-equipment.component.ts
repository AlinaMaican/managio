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
export class ExpiringEquipmentsComponent implements OnInit, OnDestroy {

  public employeeEquipmentSubscription: Subscription;
  expiringEquipments: ExpiringEquipmentModel[];

  baseURL: string = 'reports/expiring-equipments';

  constructor(private route: ActivatedRoute,
              public expiringEquipmentService: ExpiringEquipmentService) {
  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
  }

  getPaginatedList(paginatedList: ExpiringEquipmentModel[]) {
    this.expiringEquipments = paginatedList;
  }

}
