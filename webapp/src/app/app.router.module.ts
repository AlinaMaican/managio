import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserListComponent} from "./users/user-list.component";
import {EditComponent} from "./users/edit/edit.component";

const routes:Routes = [
  {path: '', component: UserListComponent, pathMatch: 'full'},
  {path: 'edit/:id', component: EditComponent,}
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRouterModule { }
