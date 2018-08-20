import { Component, OnInit } from '@angular/core';
import {User} from "../model/user.model";
import {UserService} from "../user.service";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  user: User;
  id: number;

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.userService.getUserById(this.id).subscribe();
    });
  }
}
