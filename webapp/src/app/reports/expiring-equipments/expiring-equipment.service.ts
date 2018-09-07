import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/index";
import {ExpiringEquipmentModel} from "./expiring-equipment.model";



@Injectable()
export class ExpiringEquipmentService {

  private expiringEquipmentUrl = environment.resourcesUrl + '/employeeEquipment/reports/expiring';

  constructor(private httpClient: HttpClient) {
  }

  getAllExpiringEquipment(): Observable<ExpiringEquipmentModel[]> {
    return this.httpClient.get<ExpiringEquipmentModel[]>(this.expiringEquipmentUrl);
  }

}
