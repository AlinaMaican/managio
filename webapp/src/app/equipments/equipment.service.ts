import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {EquipmentModel} from "./model/equipment.model";
import index from "@angular/cli/lib/cli";

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

  saveFile(importedFile: File){
    const formData: FormData = new FormData();
    formData.append('file', importedFile, importedFile.name);
    return this.httpClient.post(this.equipmentUrl + '/importByFile', formData);
  }

  getAllAvailableEquipments() {
    return this.httpClient.get<EquipmentModel[]>(this.equipmentUrl + '/available');
  }

  saveAllocatedEquipments(selectedEquipments: EquipmentModel[], employeeId: number) {
    return this.httpClient.post<EquipmentModel[]>(this.equipmentUrl + '/saveAllocatedEquipments/' + employeeId, selectedEquipments, {});
  }

  getFilteredEquipments(searchValue: string): Observable<EquipmentModel[]> {
    let Param = new HttpParams();
    Param = Param.append('name_contains',searchValue);
    return this.httpClient.get<EquipmentModel[]>(this.equipmentUrl,{params:Param});
  }
}
