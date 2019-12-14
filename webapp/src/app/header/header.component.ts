import { Component, OnInit } from '@angular/core';
import {User} from "../users/model/user.model";
import {UserService} from "../users/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loggedUser:User;
  isAdmin=false;
  constructor(private service: UserService) { }

  ngOnInit() {
    this.service.getAuthUser().subscribe(
      (user: User) => {
        this.loggedUser=user;
        if(this.loggedUser.userRole.toLocaleString()  === "ADMIN"){
          this.isAdmin=true;
        }
      }
    );
  }
}




