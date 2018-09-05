import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {ExpiringEquipmentModel} from "./expiring-equipment.model";
import {ExpiringEquipmentService} from "./expiring-equipment.service";


@Component({
  selector: 'app-expiring-equipment',
  templateUrl: './expiring-equipment.component.html',
  styleUrls: ['./expiring-equipment.component.css']
})
export class ExpiringEquipmentsComponent implements OnInit{


  ngOnInit(): void {
  }

}
