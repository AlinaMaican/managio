
export class Page<T> {

  constructor(public content: [T],
              public pageable: Pageable,
              public last: boolean,
              public totalPages: number,
              public totalElements: number,
              public numberOfElements: number,
              public first: boolean,
              public sort: Sort,
              public size: number,
              public number: number
              ) {}

}

export class Pageable {

  constructor(public sort: Sort,
              public pageSize: number,
              public pageNumber: number,
              public offset: number,
              public paged: boolean,
              public unpaged: boolean
  ) {}

}

export class Sort {

  constructor(public sorted: boolean,
              public unsorted: boolean
  ) {}

}
