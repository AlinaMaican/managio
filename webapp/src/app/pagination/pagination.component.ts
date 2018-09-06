import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {Page} from "../users/model/page.model";
import {isUndefined} from "util";
import {Service} from "../service";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent<T> implements OnInit {

  DEFAULT_PAGE_NUMBER: number = 1;
  DEFAULT_PAGE_SIZE: number = 5;
  PAGE_SIZES: number[] = [5, 10, 15];

  pageNumber: number = this.DEFAULT_PAGE_NUMBER;
  pageSize: number = this.DEFAULT_PAGE_SIZE;
  totalPages: number;

  @Input() service: Service<T>;
  @Input() subscription: Subscription;
  @Input() router: Router;
  @Input() baseURL: string;
  @Input() route: ActivatedRoute;

  @Output() retrieveContent = new EventEmitter<any[]>();

  constructor() {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.pageNumber = +params['page'];
      this.pageSize = +params['size'];
      if (isNaN(this.pageNumber) || isNaN(this.pageSize) ||
        !this.PAGE_SIZES.includes(this.pageSize) || this.pageNumber < 1) {
        this.pageNumber = this.DEFAULT_PAGE_NUMBER;
        this.pageSize = this.DEFAULT_PAGE_SIZE;
      }
      this.getAll();
    });

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

  getAll() {
    this.router.navigate([this.baseURL], { queryParams: { page: this.pageNumber, size: this.pageSize } });
    if(!isUndefined(this.subscription)) this.subscription.unsubscribe();
    this.subscription = this.service.getAll(this.pageNumber - 1, this.pageSize).subscribe(
      (page: Page<T>) => {
        this.totalPages = page.totalPages;
        if (this.pageNumber > this.totalPages) {
          this.onFirst(this.pageSize);
        } else {
          this.retrieveContent.emit(page.content);
        }
      }
    );
  }

}
