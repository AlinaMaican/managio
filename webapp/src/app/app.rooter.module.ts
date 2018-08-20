import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {AddUserComponent} from "./users/add-user/add-user.component";

const routes: Routes = [
  {path: '', component: UserListComponent},
  {path: 'add-user', component: AddUserComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRooterModule { }
