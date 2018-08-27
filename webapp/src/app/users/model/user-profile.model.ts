export class UserProfileModel {

  constructor(public id: number,
              public username: string,
              public firstName: string,
              public lastName: string,
              public password: string,
              public resetPassword: string) {
  }
}
