package co.istad.ecommerceapi.features.user;

import co.istad.ecommerceapi.features.user.dto.UpdateUserRequest;
import co.istad.ecommerceapi.features.user.dto.UserRequest;
import co.istad.ecommerceapi.features.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    UserResponse deleteByUuid(String uuid);

    UserResponse enableByUuid(String uuid);

    UserResponse disableByUuid(String uuid);

    UserResponse updateUser(String uuid, UpdateUserRequest updateUserRequest);

    UserResponse createNew(UserRequest userRequest);

    UserResponse findByUuid(String uuid);

    List<UserResponse> findAll();
}
