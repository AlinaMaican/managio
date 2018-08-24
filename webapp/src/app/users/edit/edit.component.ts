import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  id: number;
  formGroup: FormGroup;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.initForm();
    });
  }

  onSubmit() {
    this.userService.updateUserById(this.id, this.formGroup.value)
      .subscribe(
        () => {
          this.router.navigateByUrl('/')
        }
      );
  }

  initForm() {
    this.formGroup = new FormGroup({
      'username': new FormControl('', Validators.required),
      'firstName': new FormControl('', [
        Validators.required, Validators.pattern('^[a-zA-Z]+(([\',. -][a-zA-Z ])?[a-zA-Z]*)*$')
      ]),
      'lastName': new FormControl('', [
        Validators.required, Validators.pattern('^[a-zA-Z]+(([\',. -][a-zA-Z ])?[a-zA-Z]*)*$')
      ]),
      'password': new FormControl('', Validators.required),
      'userRole': new FormControl('', Validators.required),
      'isActive': new FormControl('', Validators.required),
      'email': new FormControl('', Validators.required)

    });

    this.userService.getUserById(this.id)
      .subscribe(
        user => {
          this.formGroup.patchValue({
            username: user.username,
            firstName: user.firstName,
            lastName: user.lastName,
            password: user.password,
            userRole: user.userRole,
            isActive: user.isActive,
            email: user.email,
          });
        });
  }

}
