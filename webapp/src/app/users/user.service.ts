import {User} from './model/user.model';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {UserWithPassword} from "./model/user-with-password.model";
import {Service} from "../service";
import {EmployeeEquipmentModel} from "../employee-equipment/employee-equipment.model";

@Injectable()
export class UserService extends Service<User>{

  public basicUrl = environment.resourcesUrl + '/user';

  constructor(public httpClient: HttpClient) {
    super();
  }

  getAuthUser(): Observable<User> {
    return this.httpClient.get<User>(this.basicUrl + '/me' );
  }

  resetPassword(userId:number,password: string): Observable<User> {
    return this.httpClient.post<User>(this.basicUrl + '/password/'+userId, password, {});
  }

  getUserById(index: number): Observable<User> {
    return this.httpClient.get<User>(this.basicUrl + '/' + index);
  }

  updateUserById(user: User, index: number): Observable<User> {
    return this.httpClient.put<User>(this.basicUrl + '/' + index, user, {});
  }

  addUser(newUser: UserWithPassword): Observable<{}> {
    return this.httpClient.post<User>(this.basicUrl, newUser, {});
  }

  deleteUser(user:User): Observable<Object> {
    return this.httpClient.delete(`${this.basicUrl}/${user.id}`);
  }
}
