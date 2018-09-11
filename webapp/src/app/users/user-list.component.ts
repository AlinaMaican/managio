import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  public userSubscription: Subscription;
  users: User[];

  baseURL: string = 'management-users';

  constructor(public userService: UserService) {

  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {

  }

  getPaginatedList(paginatedList: User[]) {
    this.users = paginatedList;
  }
}
