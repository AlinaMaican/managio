import {NgModule} from '@angular/core';
import {ExtraOptions, RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {UserProfileComponent} from "./users/user-profile/user-profile.component";
import {EditComponent} from "./users/edit/edit.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EquipmentsComponent} from "./equipments/equipments.component";
import {EmployeeListComponent} from "./employees/employee-list/employee-list.component";
import {EmployeeEquipmentsListComponent} from "./employee-equipments-list/employee-equipments-list.component";
import {ExpiringEquipmentsComponent} from "./reports/expiring-equipments/expiring-equipment.component";



const routes: Routes = [
  {path: '', redirectTo: 'management-users', pathMatch: 'full'},
  {path: 'management-users', component: UserListComponent},
  {path: 'management-users/edit/:id', component: EditComponent},
  {path: 'management-users/add', component: AddUserComponent},
  {path: 'edit/:id', component: EditComponent},
  {path: 'equipment', component:EquipmentsComponent},
  {path: 'resetPassword', component: UserProfileComponent},
  {path: 'employee', component: EmployeeListComponent},
  {path: 'employeeEquipmentsList', component: EmployeeEquipmentsListComponent},
  {path: 'reports/expiring-equipments', component: ExpiringEquipmentsComponent}
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRouterModule { }
