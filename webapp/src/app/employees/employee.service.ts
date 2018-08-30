import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Injectable} from "@angular/core";
import {Employee} from "./employee.model";

@Injectable()
export class EmployeeService {
  private employeesUrl = environment.resourcesUrl + '/employee';

  constructor(private httpClient: HttpClient) {
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.employeesUrl + '/all');
  }

}
