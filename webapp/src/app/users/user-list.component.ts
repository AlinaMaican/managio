import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from './model/user.model';
import {UserService} from './user.service';
import {Subscription} from 'rxjs/internal/Subscription';
import {ActivatedRoute, Params, Router} from "@angular/router";
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
    this.subscription.unsubscribe();
  }

}
