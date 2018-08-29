import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserService} from "../user.service";
import {Subscription} from "rxjs";
import {User} from "../model/user.model";
import {UserProfileModel} from "../model/user-profile.model";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})

export class UserProfileComponent implements OnInit {
  subscription: Subscription = null;
  userProfileForm: FormGroup;
  userProfileModel: UserProfileModel = {
    'id': undefined,
    'username': '',
    'firstName': '',
    'lastName': '',
    'password': '',
    'resetPassword': '',
    'email' : ''
  };
  minLength8Min1LetterMin1Number = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

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
        this.userProfileModel = user;
      }
    );
  }

  initForm() {
    this.userProfileForm = new FormGroup({
      'password': new FormControl('', Validators.compose(
        [Validators.required, Validators.pattern(this.minLength8Min1LetterMin1Number)]
      )),
      'confirmPassword': new FormControl('', Validators.compose([Validators.required]))
    });
  }

  resetPassword() {
    this.userService.resetPassword(this.userProfileForm.value.password).subscribe(
      () => {this.router.navigateByUrl('/')}
    );
  }

  getField(fieldName: string): any {
    return this.userProfileForm.get(fieldName);
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

  validatePasswordsEquality(password: string, confirmPassword: string): boolean {
    if (confirmPassword !== password) {
      return true;
    }
    return false;
  }

  passwordMatchesConditions(userProfileForm: FormGroup): boolean {
    let password: string = userProfileForm.get('password').value;
    let confirmPassword: string = userProfileForm.get('confirmPassword').value;
    if(password.match(this.minLength8Min1LetterMin1Number)
      && confirmPassword.match(this.minLength8Min1LetterMin1Number)
      && password === confirmPassword){
      return true;
    } else{
      return false;
    }
  }

  onClickCancelButton() {
    return this.router.navigateByUrl('/');
  }
}
