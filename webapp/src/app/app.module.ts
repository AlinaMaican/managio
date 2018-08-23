import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserListComponent} from './users/user-list.component';
import {DetailsComponent} from './users/details/details.component';
import {FormComponent} from './users/form/form.component';
import {UserService} from './users/user.service';
import {HttpClientModule} from '@angular/common/http';
import {AddUserComponent} from "./users/add-user/add-user.component";
import {AppRouterModule} from "./app.router.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    AddUserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
