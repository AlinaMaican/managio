import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserListComponent} from './users/user-list.component';
import {DetailsComponent} from './users/details/details.component';
import {FormComponent} from './users/form/form.component';
import {UserService} from './users/user.service';
import {HttpClientModule} from '@angular/common/http';
import {AppRouterModule} from "./app.router.module";
import {EditComponent} from './users/edit/edit.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AddUserComponent} from "./users/add-user/add-user.component";
import { EquipmentsComponent } from './equipments/equipments.component';
import {EquipmentService} from "./equipments/equipment.service";

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    EditComponent,
    AddUserComponent,
    EquipmentsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [UserService, EquipmentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
