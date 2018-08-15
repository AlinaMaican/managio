import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { DetailsComponent } from './user/details/details.component';
import { AddeditComponent } from './user/addedit/addedit.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    DetailsComponent,
    AddeditComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
