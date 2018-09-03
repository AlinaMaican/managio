import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";

@Injectable()
export class EquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/api/equipment';

  constructor(private httpClient: HttpClient) {
  }

  getAllEquipments(): Observable<EquipmentModel[]> {
    return this.httpClient.get<EquipmentModel[]>(this.equipmentUrl + '/all');
  }

  addEquipment(newEquipmentModel:EquipmentModel ):Observable<{}>{
    return this.httpClient.post<EquipmentModel>(this.equipmentUrl,newEquipmentModel,{});
  }
}
