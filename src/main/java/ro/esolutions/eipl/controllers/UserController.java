package ro.esolutions.eipl.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.eipl.configurations.UserDetailsImpl;
import ro.esolutions.eipl.exceptions.BindingValidationException;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.models.UserModelWithPassword;
import ro.esolutions.eipl.models.validators.UsernameEmailValidator;
import ro.esolutions.eipl.services.UserService;

import javax.validation.Valid;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UsernameEmailValidator usernameEmailValidator;

    @PostMapping
    public ResponseEntity<UserModel> addNewUser(@RequestBody @Valid UserModelWithPassword userModel, BindingResult bindingResult) {
        usernameEmailValidator.validate(userModel, bindingResult);
        throwIfErrors(bindingResult);
        userModel.setId(null);
        return ResponseEntity.ok(userService.addUser(userModel));
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
    public ResponseEntity<Object> editUserById(@RequestBody @Valid final UserModel userModel,
                                               final BindingResult bindingResult,
                                               @PathVariable("user_id") final Long userId) {
        userModel.setId(userId);
        usernameEmailValidator.validate(userModel, bindingResult);
        throwIfErrors(bindingResult);
        return ResponseEntity.ok(userService.editUserById(userId, userModel));
    }

    @GetMapping("/me")
    public ResponseEntity<UserModel> getAuthUser() {
        return ResponseEntity.ok(userService.getUserById(getAuthenticatedIdOrThrow()));
    }

    @PostMapping("/password")
    public ResponseEntity<UserModel> resetPassword(@RequestBody final String newPassword) {
//      newPassword can never be empty because otherwise a HttpMessageNotReadableException would be thrown
        //  TODO stefan.popescu - 2018-09-03T03:41:43 use Spring provided @NotBlank validator
        AbstractBindingResult bindingResult = new AbstractBindingResult("password") {
            @Override
            public Object getTarget() {
                return "password";
            }

            @Override
            protected Object getActualFieldValue(String field) {
                return newPassword;
            }
        };
        if (Strings.isBlank(newPassword)) bindingResult.rejectValue("password","NotBlank");
        throwIfErrors(bindingResult);
        return ResponseEntity.ok(userService.changePasswordById(getAuthenticatedIdOrThrow(), newPassword));
    }

    private void throwIfErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new BindingValidationException(bindingResult);
    }

    private Long getAuthenticatedIdOrThrow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return ((UserDetailsImpl) auth.getPrincipal()).getId();
        }
        throw new RuntimeException("Unauthorized access and something went horribly wrong in the code");
    }
}