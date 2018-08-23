import { Component, OnInit } from '@angular/core';
import {User} from "../model/user.model";
import {UserService} from "../user.service";
import {ActivatedRoute, Params} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  user: User;
  id: number;

  edituser: FormGroup;

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.userService.getUserById(this.id).subscribe();
      this.initForm();
    });
  }

  onSubmit() {
    // console.log(this.edituser.get('firstName').errors);
    this.userService.updateUserById(this.id, this.edituser.value).subscribe(
      () => {}
    );
  }


  initForm() {

    this.edituser = new FormGroup({
      'username': new FormControl('', Validators.required),
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required),
      'userRole': new FormControl('', Validators.required),
      'isActive': new FormControl('', Validators.required),

    });

    this.userService.getUserById(this.id).subscribe(
      (user) => {
        this.edituser.patchValue({
          username: user.username.trim(),
          firstName: user.firstName,
          lastName: user.lastName.trim(),
          password: user.password.trim(),
          userRole: user.userRole,
          isActive: user.isActive
        });
    });

  }

}
