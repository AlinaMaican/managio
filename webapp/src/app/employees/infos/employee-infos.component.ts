import { Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {EmployeeService} from '../employee.service';
import {Employee} from '../employee.model';

@Component({
  selector: 'app-infos',
  templateUrl: './employee-infos.component.html',
  styleUrls: ['./employee-infos.component.css']
})
export class InfosComponent implements OnInit {

  editEmployeeForm: FormGroup;
  employee: Employee;
  firstName: String;
  lastName: String;
  REGEX_NAME: '^[A-Z0-9]+';
  private id: number;

  constructor(private employeeService: EmployeeService,
              private route: ActivatedRoute,
              private router: Router) {
  }
  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.initForm();
    });
  }
  initForm() {
    this.editEmployeeForm = new FormGroup({
      'workingStation': new FormControl(''),
      'helmetSize': new FormControl('', Validators.pattern(this.REGEX_NAME)),
      'clothingSize': new FormControl('', Validators.pattern(this.REGEX_NAME)),
      'footwearSize': new FormControl('', Validators.pattern(this.REGEX_NAME))
    });
    this.employeeService.getEmployeeById(this.id)
      .subscribe(
        employee => {
          this.firstName = employee.firstName;
          this.lastName = employee.lastName;
          this.editEmployeeForm.patchValue({
            workingStation : employee.workingStation,
            helmetSize : employee.helmetSize,
            clothingSize : employee.clothingSize,
            footwearSize : employee.footwearSize,
          });
        });
  }
  onSubmit() {
    this.employeeService.updateEmployeeById(this.editEmployeeForm.value, this.id)
    this.employeeService.updateEmployeeById(this.editEmployeeForm.value, this.id)
      .subscribe(
        () => {
          this.router.navigateByUrl('/employee');
        }
      );
  }

}
