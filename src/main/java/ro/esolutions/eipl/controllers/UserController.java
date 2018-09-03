package ro.esolutions.eipl.controllers;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.services.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private static final String BINDING_RESULT_ERROR_MESSAGE = "User model not valid";
    private static final String PASSWORD_ERROR = "Password not valid";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> addNewUser(@RequestBody @Valid UserModel userModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", BINDING_RESULT_ERROR_MESSAGE));
        }
        userModel.setId(null);
        return ResponseEntity.ok(userService.addNewUser(userModel));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserModel>> getAllUsers(@RequestParam(defaultValue = "0", name = "page") int page,
                                                       @RequestParam(defaultValue = "2", name = "size") int size) {

        return ResponseEntity.ok(userService.getAllUsers(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserModel> deleteUserById(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Object> editUserById(@RequestBody @Valid UserModel userModel,
                                               final BindingResult bindingResult,
                                               @PathVariable("user_id") Long userId) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", BINDING_RESULT_ERROR_MESSAGE));
        }
        return ResponseEntity.ok(userService.editUserById(userId, userModel));
    }

    @GetMapping("/me")
    public ResponseEntity<UserModel> getAuthUser() { return ResponseEntity.ok(userService.getFirstUser());
    }

    @PostMapping("/password")
    public ResponseEntity<Object> resetPassword(@RequestBody final String newPassword) {
        if(!Strings.isNotBlank(newPassword)) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", PASSWORD_ERROR));
        }
        return ResponseEntity.ok(userService.changePasswordById(1L, newPassword));
    }
}