import {Page} from "./users/model/page.model";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {Service} from "./service";

export class Paginable<T> {
  DEFAULT_PAGE_NUMBER: number = 1;
  DEFAULT_PAGE_SIZE: number = 5;
  PAGE_SIZES: number[] = [5, 10, 15];

  pageNumber: number = this.DEFAULT_PAGE_NUMBER;
  pageSize: number = this.DEFAULT_PAGE_SIZE;
  totalPages: number;
  baseURL: string;
  route: ActivatedRoute;

  router: Router;
  subscription: Subscription;
  list: T[];
  service: Service<T>;

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

  init(): void {
    this.route.queryParams.subscribe((params) => {
      this.pageNumber = +params['page'];
      this.pageSize = +params['size'];
      if (isNaN(this.pageNumber) || isNaN(this.pageSize) ||
        !this.PAGE_SIZES.includes(this.pageSize) || this.pageNumber < 1) {
        this.pageNumber = this.DEFAULT_PAGE_NUMBER;
        this.pageSize = this.DEFAULT_PAGE_SIZE;
        this.router.navigate([this.baseURL], { queryParams: { page: this.pageNumber, size: this.pageSize } });
      }
    });
    this.getAll();
  }

  getAll(): void {
    this.router.navigate([this.baseURL], { queryParams: { page: this.pageNumber, size: this.pageSize } });
    this.subscription = this.service.getAll(this.pageNumber - 1, this.pageSize).subscribe(
      (page: Page<T>) => {
        this.totalPages = page.totalPages;
        if (this.pageNumber > this.totalPages) {
          this.onFirst(this.pageSize);
        } else {
          this.list = page.content;
        }
      }
    );
  }

}
