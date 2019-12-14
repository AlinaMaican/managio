import {Observable} from "../../node_modules/rxjs/internal/Observable";
import {Page} from "./users/model/page.model";
import {HttpClient} from "../../node_modules/@angular/common/http";
import {User} from "./users/model/user.model";

export class Service<T> {

  httpClient: HttpClient;
  basicUrl: string;

  getAll(page: number, size: number): Observable<Page<T>> {
    return this.httpClient.get<Page<T>>(this.basicUrl + '/all?page=' + page + '&size=' + size);
  }
  getLoggedUser(){
    return this.httpClient.get<User>(this.basicUrl+"/me");
  }
}
