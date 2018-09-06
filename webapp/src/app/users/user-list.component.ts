import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  public userSubscription: Subscription;
  users: User[];

  baseURL: string = 'management-users';

  constructor(public userService: UserService,
              public router: Router,
              public route: ActivatedRoute) {

  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }

  getPaginatedList(paginatedList: any[]) {
    this.users = paginatedList;
  }
}
