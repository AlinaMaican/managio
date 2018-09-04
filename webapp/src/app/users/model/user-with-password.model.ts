import {UserRole} from "./user-role.model";

export class UserWithPassword {
  constructor(public id: number,
              public username: string,
              public firstName: string,
              public lastName: string,
              public resetPassword: string,
              public userRole: UserRole,
              public isActive: boolean,
              public email: string
  ) {
  }
}
