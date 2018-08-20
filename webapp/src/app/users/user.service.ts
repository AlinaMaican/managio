import {UserRole} from './model/user-role.model';
import {User} from './model/user.model';
import {of} from 'rxjs/internal/observable/of';
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
    // console.log(this.usersUrl + '/all');
    return this.httpClient.get<User[]>(this.usersUrl + '/all');
  }
}
