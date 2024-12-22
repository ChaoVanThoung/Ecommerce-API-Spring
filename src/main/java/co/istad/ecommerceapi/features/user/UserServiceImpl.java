package co.istad.ecommerceapi.features.user;

import co.istad.ecommerceapi.features.auth.RoleRepository;
import co.istad.ecommerceapi.domain.Role;
import co.istad.ecommerceapi.domain.User;
import co.istad.ecommerceapi.features.user.dto.UpdateUserRequest;
import co.istad.ecommerceapi.features.user.dto.UserRequest;
import co.istad.ecommerceapi.features.user.dto.UserResponse;
import co.istad.ecommerceapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public UserResponse deleteByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"
                ));
        userRepository.delete(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse enableByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"
                ));
        user.setIsDeleted(false);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse disableByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"
                ));
        user.setIsDeleted(true);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(String uuid, UpdateUserRequest updateUserRequest) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"
                ));
        if (userRepository.existsByName(updateUserRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username already exists"
            );
        }

        if (userRepository.existsByEmail(updateUserRequest.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists"
            );
        }

        if (userRepository.existsByPhoneNumber(updateUserRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Phone number already exists"
            );
        }
        userMapper.fromUpdateUserRequestPartially(updateUserRequest, user);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse createNew(UserRequest userRequest) {

        if (userRepository.existsByName(userRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username already exists"
            );
        }

        if (userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists"
            );
        }

        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Phone number already exists"
            );
        }

        if (!userRequest.password().equals(userRequest.confirmPassword())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Password does not match"
            );
        }

        User user = userMapper.fromUserRequest(userRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setIsVerified(false);
        user.setIsDeleted(false);
        user.setIsAccountNonLocked(true);
        user.setIsAccountNonExpired(true);
        user.setIsCredentialsNonExpired(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).orElseThrow());
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse findByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"
                ));

        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserListResponse(users);
    }
}
