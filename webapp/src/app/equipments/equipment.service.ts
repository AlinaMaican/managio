import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";

@Injectable()
export class EquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/equipment';

  constructor(private httpClient: HttpClient) {
  }

  getAllEquipments(): Observable<EquipmentModel[]> {
    return this.httpClient.get<EquipmentModel[]>(this.equipmentUrl + '/all');
  }

  addEquipment(newEquipmentModel: EquipmentModel): Observable<{}> {
    return this.httpClient.post<EquipmentModel>(this.equipmentUrl, newEquipmentModel, {});
  }

  getAllEquipmentByEmployeeId(employeeId: number): Observable<EquipmentModel[]> {
    return this.httpClient.get<EquipmentModel[]>(environment.resourcesUrl + `/employee/${employeeId}/equipment`);
  }

  saveFile(importedFile: File){
    const formData: FormData = new FormData();
    formData.append('file', importedFile, importedFile.name);
    return this.httpClient.post(this.equipmentUrl + '/file', formData);
  }
}
