import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {UserProfileComponent} from "./users/user-profile/user-profile.component";

const routes: Routes = [
  {path: '', component: UserListComponent},
  {path: 'resetPassword', component: UserProfileComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRooterModule { }
