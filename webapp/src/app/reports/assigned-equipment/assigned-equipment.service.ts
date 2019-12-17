import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/index";
import {Service} from "../../service";
import {AssignedEquipmentModel} from "./assigned-equipment.model";


@Injectable()
export class AssignedEquipmentService extends Service<AssignedEquipmentModel>{

  public basicUrl = environment.resourcesUrl + '/employeeEquipment';

  constructor(public httpClient: HttpClient) {
    super();
  }

  getAllAssignedEquipment(): Observable<AssignedEquipmentModel[]> {
    return this.httpClient.get<AssignedEquipmentModel[]>(this.basicUrl+'/getAll');
  }

}
