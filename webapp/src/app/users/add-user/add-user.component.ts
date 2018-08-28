import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserService} from "../user.service";
import {User} from "../model/user.model";
import {Subscription} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {FieldError} from "../model/field-error.model";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit, OnDestroy {
  subscription: Subscription = null;
  adduser: FormGroup;
  isUsernameError = false;
  usernameFieldError: FieldError;
  isEmailError = false;
  emailFieldError: FieldError;

  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.adduser = new FormGroup({
      'username': new FormControl('', Validators.required),
      'password': new FormControl('', [Validators.required, Validators.minLength(4)]),
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'userRole': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
    });
  }

  createUser() {
    let userObject: User = this.adduser.value;
    userObject.isActive = true;
    this.subscription =
      this.userService.addUser(userObject).subscribe(
        () => {
          this.router.navigate(['/']);
        },
        (response: HttpErrorResponse) => {
          var error = response.error;
          this.handleErrors(new Map(Object.entries(response.error)))
        }
      );
  }

  ngOnDestroy(): void {
    if (this.subscription !== null) {
      this.subscription.unsubscribe();
    }
  }

  private handleErrors(fieldErrorsMap: Map<String, FieldError>) {
    this.resetErrors()
    fieldErrorsMap.forEach(
      (fieldError: FieldError, fieldName: String) => {
        switch (fieldName) {
          case 'username': {
            this.isUsernameError = true;
            this.usernameFieldError = fieldError;
          }
            break;
          case 'email': {
            this.isEmailError = true;
            this.emailFieldError = fieldError;
          }
            break;
          default:
            throw new Error('Field error not managed');
        }
      });

  }

  private resetErrors() {
    this.isEmailError = false;
    this.emailFieldError = null;

    this.isUsernameError = false;
    this.usernameFieldError = null;
  }
}
