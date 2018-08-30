import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";

@Injectable()
export class EquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/equipment';

  constructor(private httpclient: HttpClient) {
  }

  getAllEquipments(): Observable<EquipmentModel[]> {
    return this.httpclient.get<EquipmentModel[]> (this.equipmentUrl + '/all');
  }
}
