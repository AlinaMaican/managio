import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserComponent } from './users/user.component';
import { DetailsComponent } from './users/details/details.component';
import { FormComponent } from './users/form/form.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    DetailsComponent,
    FormComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
