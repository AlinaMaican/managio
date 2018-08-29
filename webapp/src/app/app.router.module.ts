import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {EditComponent} from "./users/edit/edit.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EquipmentsComponent} from "./equipments/equipments.component";

const routes: Routes = [
  {path: '', component: UserListComponent, pathMatch: 'full'},
  {path: 'edit/:id', component: EditComponent},
  {path: 'add-user', component: AddUserComponent},
  {path: 'equipment', component:EquipmentsComponent}
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRouterModule { }
