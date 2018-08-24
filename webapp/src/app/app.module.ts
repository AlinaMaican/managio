import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list.component';
import { DetailsComponent } from './users/details/details.component';
import { FormComponent } from './users/form/form.component';
import { UserService } from './users/user.service';
import {HttpClientModule} from '@angular/common/http';
import {AppDropdownDirective} from "./shared/app-dropdown.directive";
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    AppDropdownDirective,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
