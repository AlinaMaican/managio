import {User} from './model/user.model';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Page} from "./model/page.model";

@Injectable()
export class UserService {

  private usersUrl = environment.resourcesUrl + '/user';

  constructor(private httpClient: HttpClient) {
  }

  // getAllUsers(): Observable<User[]> {
  //   return this.httpClient.get<User[]>(this.usersUrl + '/all');
  // }

  getAllUsers(page: number, size: number): Observable<Page<User>> {
    return this.httpClient.get<Page<User>>(this.usersUrl + '/all?page=' + page + '&size=' + size);
  }

  getAuthUser(): Observable<User> {
    return this.httpClient.get<User>(this.usersUrl + '/me' );
  }

  resetPassword(password: string): Observable<User> {
    return this.httpClient.post<User>(this.usersUrl + '/password', password, {});
  }

  getUserById(index: number): Observable<User> {
    return this.httpClient.get<User>(this.usersUrl + '/' + index);
  }

  updateUserById(user: User, index: number): Observable<User> {
    return this.httpClient.put<User>(this.usersUrl + '/' + index, user, {});
  }


  addUser(newUser: User): Observable<{}> {
    return this.httpClient.post<User>(this.usersUrl, newUser, {});
  }
}
