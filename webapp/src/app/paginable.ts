import {Page} from "./users/model/page.model";
import {Router} from "@angular/router";
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

  getAll(): void {
    this.router.navigateByUrl(this.baseURL + '/page/' + this.pageNumber + '/size/' + this.pageSize);
    this.subscription = this.service.getAllUsers(this.pageNumber - 1, this.pageSize).subscribe(
      (page: Page<T>) => {
        this.list = page.content;
        this.totalPages = page.totalPages;
      }
    );
  }

}
