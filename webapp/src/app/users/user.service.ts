import {User} from './model/user.model';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {UserWithPassword} from "./model/user-with-password.model";
import {Service} from "../service";
import {Page} from "./model/page.model";

@Injectable()
export class UserService {

  private basicUrl = environment.resourcesUrl + '/user';

  constructor(private httpClient: HttpClient) {
  }

  getAll(page: number, size: number): Observable<Page<User>> {
    return this.httpClient.get<Page<User>>(this.basicUrl + '/all?page=' + page + '&size=' + size);
  }

  getAuthUser(): Observable<User> {
    return this.httpClient.get<User>(this.basicUrl + '/me' );
  }

  resetPassword(password: string): Observable<User> {
    return this.httpClient.post<User>(this.basicUrl + '/password', password, {});
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
}
