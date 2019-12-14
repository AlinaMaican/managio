import {Component, OnDestroy, OnInit} from "@angular/core";

import {ActivatedRoute} from "@angular/router";
import {EquipmentModel} from "../../equipments/model/equipment.model";
import {EquipmentService} from "../../equipments/equipment.service";
import {environment} from "../../../environments/environment";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-expiring-equipment',
  templateUrl: './expiring-equipment.component.html',
  styleUrls: ['./expiring-equipment.component.css']
})
export class ExpiringEquipmentsComponent implements OnInit, OnDestroy {

  expiringEquipments: EquipmentModel[];
  expiredEquipmentsCSVDownloadUrl = environment.resourcesUrl + '/equipment/reports/expired?type=text/csv';

  constructor(private route: ActivatedRoute,
              public equipmentService: EquipmentService,
              public datePipe:DatePipe) {
  }

  ngOnInit(): void {
    this.equipmentService.getExpiredEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.expiringEquipments = equipments;
      });
  }

  ngOnDestroy(): void {
  }

  getDownloadName(): string {
    return 'expiredEquipments-' + this.datePipe.transform(new Date(), 'yyyy-MM-dd') + ".csv";
  }
}
