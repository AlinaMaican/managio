import {UserRole} from './user-role.model';

export class User {

  constructor(public id: number,
              public username: string,
              public firstName: string,
              public lastName: string,
              public password: string,
              public resetPassword: string,
              public userRole: UserRole,
              public isActive: boolean,
              public email: string
  ) {
  }
}
