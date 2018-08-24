import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EditComponent} from "./users/edit/edit.component";

const routes: Routes = [
  {path: '', component: UserListComponent, pathMatch: 'full'},
  {path: 'add-user', component: AddUserComponent},
  {path: 'edit/:id', component: EditComponent,},
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRouterModule { }
