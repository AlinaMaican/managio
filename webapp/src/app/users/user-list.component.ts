import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {Router} from "@angular/router";
import {Page} from "./model/page.model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  private usersSubscription: Subscription;
  users: User[];

  pageNumber: number = 1;
  pageSize: number = 5;
  totalPages: number;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  ngOnDestroy(): void {
    this.usersSubscription.unsubscribe();
  }

  getAll(): void {
    this.usersSubscription = this.userService.getAllUsers(this.pageNumber - 1, this.pageSize).subscribe(
      (users: Page<User>) => {
        this.users = users.content;
        this.totalPages = users.totalPages;
      }
    );
  }

  onPrev(): void {
    this.pageNumber--;
    this.getAll();
  }

  onNext(): void {
    this.pageNumber++;
    this.getAll();
  }

  onFirst(newPageSize: number): void {
    this.pageNumber = 1;
    this.pageSize = newPageSize;
    this.getAll();
  }

  onLast(): void {
    this.pageNumber = this.totalPages;
    this.getAll();
  }
}

