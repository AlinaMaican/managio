import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  usersSubscription: Subscription;

  users: User[];

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.usersSubscription = this.userService.getAllUsers().subscribe(
      (users: User[]) => {
        this.users = users;
      }
    );
  }



  ngOnDestroy(): void {
    this.usersSubscription.unsubscribe();
  }

}
