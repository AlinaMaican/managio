import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaderResponse, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Injectable} from '@angular/core';
import {Employee} from './employee.model';



@Injectable()
export class EmployeeService {
  private employeeUrl = environment.resourcesUrl + '/employee';

  constructor(private httpClient: HttpClient) {
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.employeeUrl + '/all');
  }
  getFilteredEmployees(searchValue: string ):Observable<Employee[]> {
    let Param = new HttpParams();
    Param = Param.append('name_contains', searchValue);
    return this.httpClient.get<Employee[]>(this.employeeUrl, {params: Param});
  }

  saveFile(importedFile: File) {
    const formData: FormData = new FormData();
    formData.append('file', importedFile, importedFile.name);
    return this.httpClient.post(this.employeeUrl + '/importByFile', formData);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(this.employeeUrl + '/' + id);
  }
  updateEmployeeById(helmetSize: String, clothingSize: String, footwearSize: String, id: number): Observable<Employee> {
    return this.httpClient.put<Employee>(this.employeeUrl + '/' + id + '?helmetSize=' + helmetSize + '&clothingSize=' + clothingSize + '&footwearSize=' + footwearSize, {});
  }
}
