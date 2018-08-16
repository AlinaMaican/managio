import {UserRole} from "./model/user-role.model";
import {User} from "./model/user.model";
import {of} from "rxjs/internal/observable/of";
import {Observable} from "rxjs/internal/Observable";

export class UserService {

  private users: User[] = [
    new User(1, 'GigiMasinuta', 'Gigi', 'Masinuta', 'vroom', new UserRole(1), true),
    new User(2, 'GigiMasinuta', 'ninle', 'Masinuta', 'vroom', new UserRole(1), false),

  ];

  getAllUsers(): Observable<User[]> {
    return of(this.users);
  }
}
