import {User} from './model/user.model';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class UserService {

  private usersUrl = environment.resourcesUrl + '/user';

  constructor(private httpClient: HttpClient) {
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.usersUrl + '/all');
  }

  getUserById(index: number): Observable<User> {
    return this.httpClient.get<User>(this.usersUrl + '/' + index);
  }

  updateUserById(index: number, user: User): Observable<User> {
    return this.httpClient.put<User>( this.usersUrl + '/' + index, user, {});
  }


  addUser(newUser: User): Observable<{}> {
    return this.httpClient.post<User>(this.usersUrl, newUser, {});
  }
}
