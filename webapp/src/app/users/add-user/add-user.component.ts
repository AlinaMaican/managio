import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserService} from "../user.service";
import {User} from "../model/user.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit, OnDestroy {
  subscription: Subscription = null;
  adduser: FormGroup;


  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.initForm();
        }
      );
  }

  initForm() {
    this.adduser = new FormGroup({
      'username': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required),
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'userRole': new FormControl('', Validators.required),
      'email': new FormControl('', Validators.required),
    });
  }

  createUser() {
   let userObject: User = this.adduser.value;
   userObject.isActive = true;
   console.log(userObject);
    this.subscription =
      this.userService.addUser(userObject).subscribe(
        () => {}
      );
  }

  ngOnDestroy(): void {
    if (this.subscription !== null) {
      this.subscription.unsubscribe();
    }
  }
}

