import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {UserProfileComponent} from "./users/user-profile/user-profile.component";
import {EditComponent} from "./users/edit/edit.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EquipmentsComponent} from "./equipments/equipments.component";
import {EmployeeComponent} from "./employees/employee.component";
import {ExpiringEquipmentsComponent} from "./reports/expiring-equipments/expiring-equipment.component";
import {AssignedEquipmentComponent} from "./reports/assigned-equipment/assigned-equipment.component";
import {EmployeeEquipmentComponent} from "./employees/equipment/employee-equipment.component";
import {EditEmployeeComponent} from "./employees/edit-employee/edit-employee.component";
import {UnusedEquipmentsComponent} from "./reports/unused-equipments/unused-equipments.component";
import {AvailableEquipmentListComponent} from "./available-equipment-list/available-equipment-list.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard',component:DashboardComponent},
  {path: 'management-users', component: UserListComponent},
  {path: 'management-users/edit/:id', component: EditComponent},
  {path: 'management-users/add', component: AddUserComponent},
  {path: 'edit/:id', component: EditComponent},
  {path: 'equipment', component: EquipmentsComponent},
  {path: 'resetPassword', component: UserProfileComponent},
  {path: 'employee', component: EmployeeComponent},
  {path: 'employee/:id/equipment', component: EmployeeEquipmentComponent},
  {path: 'employee/:id', component:EditEmployeeComponent},
  {path: 'reports/expiring-equipments', component: ExpiringEquipmentsComponent},
  {path: 'equipments/:employeeId', component: AvailableEquipmentListComponent},
  {path: 'reports/unused-equipments', component: UnusedEquipmentsComponent},
  {path: 'reports/assigned-equipments', component: AssignedEquipmentComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRouterModule {
}
