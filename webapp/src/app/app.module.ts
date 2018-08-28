import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import { EditComponent} from './users/edit/edit.component';
import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list.component';
import { DetailsComponent } from './users/details/details.component';
import { FormComponent } from './users/form/form.component';
import { UserService } from './users/user.service';
import { HttpClientModule} from '@angular/common/http';
import { UserProfileComponent } from "./users/user-profile/user-profile.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRouterModule } from "./app.router.module";

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    EditComponent,
    FormComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRouterModule,
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
