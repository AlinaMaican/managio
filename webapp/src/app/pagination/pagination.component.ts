import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  // DEFAULT_PAGE_NUMBER: number = 1;
  // DEFAULT_PAGE_SIZE: number = 5;
  PAGE_SIZES: number[] = [5, 10, 15];

  @Input() pageNumber: number;
  @Input() pageSize: number;
  @Input() totalPages: number;

  @Output() goPrev = new EventEmitter<boolean>();
  @Output() goNext = new EventEmitter<boolean>();
  @Output() goFirst = new EventEmitter<number>();
  @Output() goLast = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() {
  }

  onPrev(): void {
    this.goPrev.emit(true);
  }

  onNext(): void {
    this.goNext.emit(true);
  }

  onFirst(newPageSize: number): void {
    this.goFirst.emit(newPageSize);
  }

  onLast(): void {
    this.goLast.emit(true);
  }

}
