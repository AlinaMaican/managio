package ro.esolutions.eipl.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> addNewUser(@RequestBody @Valid UserModel userModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.addNewUser(userModel));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("user_id") Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserModel> deleteUserById(@PathVariable("user_id") Integer userId){
        return ResponseEntity.ok(userService.deleteUserById());
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<UserModel> editUserById(@RequestBody @Valid UserModel userModel, @PathVariable("user_id") Integer userId){
        return ResponseEntity.ok(userService.editUserById(userId, userModel));
    }


}
