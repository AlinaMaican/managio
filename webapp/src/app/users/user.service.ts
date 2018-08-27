import {User} from './model/user.model';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {UserProfileModel} from "./model/user-profile.model";

@Injectable()
export class UserService {

  private usersUrl = environment.resourcesUrl + '/user';

  constructor(private httpClient: HttpClient) {
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.usersUrl + '/all');
  }

  getAuthUser(): Observable<User> {
    return this.httpClient.get<User>(this.usersUrl + '/me' );
  }

  resetPassword(password: string): Observable<User> {
    return this.httpClient.post<User>(this.usersUrl + '/password', password, {});
  }
}
