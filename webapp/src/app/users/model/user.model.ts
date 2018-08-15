import {UserRole} from "./user-role.model";

export class User {

  constructor(public id: number,
              public username: String,
              public firstName: String,
              public lastName: String,
              public password: String,
              public userRole: UserRole,
              public status: boolean
  ) {
  }
}
