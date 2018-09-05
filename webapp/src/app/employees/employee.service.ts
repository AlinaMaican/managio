import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Injectable} from "@angular/core";
import {Employee} from "./employee.model";
import {EquipmentModel} from "../equipments/model/equipment.model";

@Injectable()
export class EmployeeService {
  private employeeUrl = environment.resourcesUrl + '/employee';


  constructor(private httpClient: HttpClient) {
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.employeeUrl + '/all');
  }
  getFilteredEmployees(searchValue:string ):Observable<Employee[]>{
    let Param = new HttpParams();
    Param = Param.append('Parameter',searchValue);
    return this.httpClient.get<Employee[]>(this.employeeUrl,{params:Param});

  }

}
