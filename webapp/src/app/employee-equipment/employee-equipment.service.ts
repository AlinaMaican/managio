import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/internal/Observable";
import {EmployeeEquipmentModel} from "./employee-equipment.model";
import {EquipmentModel} from "../equipments/model/equipment.model";

@Injectable()
export class EmployeeEquipmentService {
  private employeeEquipmentUrl = environment.resourcesUrl + '/employeeEquipment';

  constructor(private httpClient: HttpClient) {
  }

  getEquipmentByEmployeeId(employeeId: number): Observable<EmployeeEquipmentModel[]> {
    return this.httpClient.get<EmployeeEquipmentModel[]>(
      this.employeeEquipmentUrl,
      {params: new HttpParams().set('employeeId', employeeId.toString())});
  }

  updateEmployeeEquipment(employeeEquipment: EmployeeEquipmentModel): Observable<EmployeeEquipmentModel> {
    return this.httpClient.put<EmployeeEquipmentModel>(`${this.employeeEquipmentUrl}/${employeeEquipment.id}`, employeeEquipment);
  }

  deleteEmployeeEquipment(employeeEquipment: EmployeeEquipmentModel): Observable<Object> {
    return this.httpClient.delete(`${this.employeeEquipmentUrl}/${employeeEquipment.id}`);
  }

  saveAllocatedEquipments(selectedEquipments: EquipmentModel[], employeeId: number) {
    return this.httpClient.post<EquipmentModel[]>(this.employeeEquipmentUrl + '/saveAllocatedEquipments/' + employeeId, selectedEquipments, {});
  }
}
