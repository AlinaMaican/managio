import {User} from "../model/user.model";
import {UserProfileModel} from "../model/user-profile.model";

export class UserProfileMapper {
  public static fromUserToUserProfile(user: User) {
    return new UserProfileModel(
      user.id,
      user.username,
      user.firstName,
      user.lastName,
      '',
      '',
      user.email
    )
  }
}
