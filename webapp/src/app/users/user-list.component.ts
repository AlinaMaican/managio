import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Page} from "./model/page.model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  private usersSubscription: Subscription;
  users: User[];

  DEFAULT_PAGE_NUMBER: number = 1;
  DEFAULT_PAGE_SIZE: number = 5;
  PAGE_SIZES: number[] = [5, 10, 15];

  pageNumber: number = 1;
  pageSize: number = 5;
  totalPages: number;
  baseURL: string = 'management-users';

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.pageNumber = +params['page'];
      this.pageSize = +params['size'];
      if (isNaN(this.pageNumber) || isNaN(this.pageSize) ||
        !this.PAGE_SIZES.includes(this.pageSize) || this.pageNumber < 1) {
        this.pageNumber = this.DEFAULT_PAGE_NUMBER;
        this.pageSize = this.DEFAULT_PAGE_SIZE;
        this.router.navigateByUrl(this.baseURL + '/page/' + this.pageNumber + '/size/' + this.pageSize);
      }
    });
    this.getAll();
  }

  ngOnDestroy(): void {
    this.usersSubscription.unsubscribe();
  }

  getAll(): void {
    this.router.navigateByUrl(this.baseURL + '/page/' + this.pageNumber + '/size/' + this.pageSize);
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

