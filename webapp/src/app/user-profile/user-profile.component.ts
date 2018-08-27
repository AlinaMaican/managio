import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserService} from "../users/user.service";
import {Subscription} from "rxjs";
import {User} from "../users/model/user.model";
import {UserProfileModel} from "../users/model/user-profile.model";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  subscription: Subscription = null;
  userprofile: FormGroup;
  user: UserProfileModel = {
    'id': undefined,
    'username': '',
    'firstName': '',
    'lastName': '',
    'password': '',
    'resetPassword': ''
  };
  min8Min1LetterMin1Number = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

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
    this.subscription = this.userService.getAuthUser().subscribe(
      (user: User) => {
        this.user = user;
      }
    );
  }

  initForm() {
    this.userprofile = new FormGroup({
      'password': new FormControl('', Validators.compose([Validators.required, Validators.pattern(this.min8Min1LetterMin1Number)])),
      'confirmPassword': new FormControl('', Validators.compose([Validators.required]))
    });
  }

  resetPassword() {
    this.userService.resetPassword(this.userprofile.value.password).subscribe(
      () => {/*success*/},
      () => {/*error*/}
    );

  }


  getField(fieldName: string): any {
    return this.userprofile.get(fieldName);
  }

  checkForErrors(fieldName: string): boolean {
    if(this.getField(fieldName).errors) {
      return true;
    }
    return false;
  }

  ngOnDestroy(): void {
    if (this.subscription !== null) {
      this.subscription.unsubscribe();
    }
  }

  validate(password: string, confirmPassword: string): boolean {
    if (confirmPassword !== password) {
      return true;
    }
    return false;
  }
}
