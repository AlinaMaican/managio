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

  updateUser(index: number, updatedUser: User) {

  }
}
