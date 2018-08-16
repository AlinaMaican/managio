import {UserRole} from "./model/user-role.model";
import {User} from "./model/user.model";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Subject} from "rxjs/internal/Subject";

export class UserService {

  private users: User[] = [
    new User(1, 'GigiMasinuta', 'Gigi', 'Masinuta', 'vroom', new UserRole(1), true),
    new User(2, 'GigiMasinuta', 'ninle', 'Masinuta', 'vroom', new UserRole(1), false),

  ];
  usersSubject = new Subject<User[]>();

  constructor(private httpClient: HttpClient) {
  }

  getAllUsers() {
    return this.usersSubject.next(this.users.slice());
  }
}
