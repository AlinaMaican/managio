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

  private id: number;
  public formGroup: FormGroup;
  private REGEX_NAME: string = '^[a-zA-Z]+(([\',. -][a-zA-Z ])?[a-zA-Z]*)*$';

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
    this.userService.updateUserById(this.formGroup.value, this.id)
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
        Validators.required, Validators.pattern(this.REGEX_NAME)
      ]),
      'lastName': new FormControl('', [
        Validators.required, Validators.pattern(this.REGEX_NAME)
      ]),
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
            userRole: user.userRole,
            isActive: user.isActive + '',
            email: user.email,
          });
        });
  }

}
