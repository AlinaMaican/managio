import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";
import {Service} from "../service";

@Injectable()
export class EquipmentService extends Service<EquipmentModel>{
  public basicUrl = environment.resourcesUrl + '/equipment';

  constructor(public httpClient: HttpClient) {
    super();
  }

  addEquipment(newEquipmentModel: EquipmentModel): Observable<{}> {
    return this.httpClient.post<EquipmentModel>(this.basicUrl, newEquipmentModel, {});
  }

  saveFile(importedFile: File){
    const formData: FormData = new FormData();
    formData.append('file', importedFile, importedFile.name);
    return this.httpClient.post(this.basicUrl + '/importByFile', formData);
  }

  getAllAvailableEquipments() {
    return this.httpClient.get<EquipmentModel[]>(this.basicUrl + '/available');
  }

  getFilteredEquipments(searchValue: string): Observable<EquipmentModel[]> {
    let Param = new HttpParams();
    Param = Param.append('name_contains',searchValue);
    return this.httpClient.get<EquipmentModel[]>(this.basicUrl,{params:Param});
  }
}
