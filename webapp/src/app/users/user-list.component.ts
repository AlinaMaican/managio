import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {ActivatedRoute, Router} from "@angular/router";
import {Paginable} from "../paginable";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent extends Paginable<User> implements OnInit, OnDestroy {

  public subscription: Subscription;
  list: User[];
  baseURL: string = 'management-users';

  constructor(public service: UserService,
              public router: Router,
              public route: ActivatedRoute) {
    super();
  }

  ngOnInit(): void {
    this.init();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
