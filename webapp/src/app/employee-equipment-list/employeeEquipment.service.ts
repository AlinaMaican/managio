import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class EmployeeEquipmentService {
  private equipmentUrl = environment.resourcesUrl + '/employeeEquipmentList';

  constructor(private httpClient: HttpClient) {
  }
}
