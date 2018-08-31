import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Equipment} from "../users/model/equipment";


@Injectable()
export class EquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/equipment';

  constructor(private httpClient: HttpClient) {
  }

  getAllEquipments(): Observable<Equipment[]> {
    return this.httpClient.get<Equipment[]> (this.equipmentUrl + '/all');
  }
  addEquipment(newEquipment:Equipment ):Observable<{}>{
    return this.httpClient.post<Equipment>(this.equipmentUrl,newEquipment,{});


  }
}
