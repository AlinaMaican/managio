import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Equipment} from "../users/model/equipment";

@Injectable()
export class EquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/equipment';

  constructor(private httpclient: HttpClient) {
  }

  getAllEquipments(): Observable<Equipment[]> {
    return this.httpclient.get<Equipment[]> (this.equipmentUrl + '/all');
  }
}
