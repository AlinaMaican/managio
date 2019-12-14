import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserListComponent} from './users/user-list.component';
import {DetailsComponent} from './users/details/details.component';
import {FormComponent} from './users/form/form.component';
import {UserService} from './users/user.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRouterModule} from './app.router.module';
import {EditComponent} from './users/edit/edit.component';
import {AddUserComponent} from './users/add-user/add-user.component';
import {UserProfileComponent} from './users/user-profile/user-profile.component';
import {EmployeeService} from './employees/employee.service';
import {EquipmentsComponent} from './equipments/equipments.component';
import {EquipmentService} from './equipments/equipment.service';
import {EmployeeComponent} from './employees/employee.component';
import {PaginationComponent} from './pagination/pagination.component';
import {ExpiringEquipmentsComponent} from './reports/expiring-equipments/expiring-equipment.component';
import {EmployeeEquipmentComponent} from './employees/equipment/employee-equipment.component';
import {AvailableEquipmentListComponent} from "./available-equipment-list/available-equipment-list.component";
import {EmployeeEquipmentService} from "./employee-equipment/employee-equipment.service";
import {UnusedEquipmentsComponent} from './reports/unused-equipments/unused-equipments.component';
import {DatePipe} from '@angular/common';
import {EditEmployeeComponent} from "./employees/edit-employee/edit-employee.component";
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AssignedEquipmentComponent} from "./reports/assigned-equipment/assigned-equipment.component";
import {AssignedEquipmentService} from "./reports/assigned-equipment/assigned-equipment.service";
import {DashboardComponent} from "./dashboard/dashboard.component";

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
    ExpiringEquipmentsComponent,
    EquipmentsComponent,
    EmployeeComponent,
    EmployeeEquipmentComponent,
    EditEmployeeComponent,
    UnusedEquipmentsComponent,
    AvailableEquipmentListComponent,
    AssignedEquipmentComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule,
    ReactiveFormsModule,
    FormsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    UserService,
    EquipmentService,
    EmployeeService,
    EmployeeEquipmentService,
    AssignedEquipmentService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
