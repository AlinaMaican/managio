import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list.component';
import { DetailsComponent } from './users/details/details.component';
import { FormComponent } from './users/form/form.component';
import { UserService } from './users/user.service';
import {HttpClientModule} from '@angular/common/http';
import {AppRouterModule} from "./app.router.module";
import { EditComponent } from './users/edit/edit.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    EditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
