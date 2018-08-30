import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {UserProfileComponent} from "./users/user-profile/user-profile.component";
import {EditComponent} from "./users/edit/edit.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EquipmentsComponent} from "./equipments/equipments.component";

const routes: Routes = [
  {path: '', redirectTo: 'management-users', pathMatch: 'full'},
  {path: 'management-users', component: UserListComponent},
  {path: 'management-users/edit/:id', component: EditComponent},
  {path: 'management-users/add', component: AddUserComponent},
  {path: 'edit/:id', component: EditComponent},
  {path: 'equipment/all', component:EquipmentsComponent},
  {path: 'resetPassword', component: UserProfileComponent},
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRouterModule { }
