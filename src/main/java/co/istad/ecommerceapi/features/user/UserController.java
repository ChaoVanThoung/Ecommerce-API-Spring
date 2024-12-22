package co.istad.ecommerceapi.features.user;

import co.istad.ecommerceapi.features.user.dto.UpdateUserRequest;
import co.istad.ecommerceapi.features.user.dto.UserRequest;
import co.istad.ecommerceapi.features.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete/{uuid}")
    UserResponse deleteByUuid(@PathVariable String uuid) {
        return userService.deleteByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/enable/{uuid}")
    UserResponse enableByUuid(@PathVariable String uuid) {
        return userService.enableByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/disable/{uuid}")
    UserResponse disableByUuid(@PathVariable String uuid){
        return userService.disableByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @PatchMapping("/update/{uuid}")
    UserResponse updateUser(@PathVariable String uuid,
                            @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(uuid, updateUserRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/createNew")
    UserResponse createNew(@RequestBody UserRequest userRequest) {
        return userService.createNew(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("search/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("findAll")
    List<UserResponse> findAll(){
        return userService.findAll();
    }
}
