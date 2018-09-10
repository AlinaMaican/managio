import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/index";
import {ExpiringEquipmentModel} from "./expiring-equipment.model";
import {Service} from "../../service";



@Injectable()
export class ExpiringEquipmentService extends Service<ExpiringEquipmentModel>{

  public basicUrl = environment.resourcesUrl + '/employeeEquipment';

  constructor(public httpClient: HttpClient) {
    super();
  }

  getAllExpiringEquipment(): Observable<ExpiringEquipmentModel[]> {
    return this.httpClient.get<ExpiringEquipmentModel[]>(this.basicUrl);
  }

}
