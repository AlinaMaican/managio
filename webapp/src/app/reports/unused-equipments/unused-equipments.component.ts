import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EquipmentService} from '../../equipments/equipment.service';
import {EquipmentModel} from '../../equipments/model/equipment.model';
import {environment} from '../../../environments/environment';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-unused-equipments',
  templateUrl: './unused-equipments.component.html',
  styleUrls: ['./unused-equipments.component.css']
})
export class UnusedEquipmentsComponent implements OnInit {
  unusedEquipments: EquipmentModel[];
  unusedEquipmentsCSVDownloadUrl = environment.resourcesUrl + '/equipment/reports/unused?type=text/csv';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private equipmentService: EquipmentService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.equipmentService.getUnusedEquipments().subscribe(
      (equipments: EquipmentModel[]) => {
        this.unusedEquipments = equipments;
      });
  }

  getDownloadName(): string {
    return 'unusedEquipments-' + this.datePipe.transform(new Date(), 'yyyy-MM-dd') + ".csv";
  }
}
