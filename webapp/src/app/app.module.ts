import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserListComponent} from './users/user-list.component';
import {DetailsComponent} from './users/details/details.component';
import {FormComponent} from './users/form/form.component';
import {UserService} from './users/user.service';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRouterModule} from "./app.router.module";
import {EditComponent} from './users/edit/edit.component';
import {AddUserComponent} from "./users/add-user/add-user.component";
import {UserProfileComponent} from "./users/user-profile/user-profile.component";
import {EmployeeService} from "./employees/employee.service";
import {EquipmentsComponent} from "./equipments/equipments.component";
import {EquipmentService} from "./equipments/equipment.service";
import { PaginationComponent } from './pagination/pagination.component';
import {EmployeeListComponent} from "./employees/employee-list/employee-list.component";
import { EmployeeEquipmentsListComponent } from './employee-equipment-list/employee-equipments-list.component';
import {EmployeeEquipmentService} from "./employee-equipment-list/employeeEquipment.service";
import { AvailableEquipmentListComponent } from './available-equipment-list/available-equipment-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    DetailsComponent,
    FormComponent,
    HeaderComponent,
    EditComponent,
    AddUserComponent,
    UserProfileComponent,
    EquipmentsComponent,
    PaginationComponent,
    EmployeeListComponent,
    EmployeeEquipmentsListComponent,
    AvailableEquipmentListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [UserService, EquipmentService, EmployeeService, EmployeeEquipmentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
